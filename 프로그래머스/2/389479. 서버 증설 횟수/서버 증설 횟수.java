import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(e->e));
        for(int i=0;i<24;i++){
            if((pq.size()+1)*m<=players[i]){
                int peopleAdd = players[i]-(pq.size()+1)*m+1;
                int serverAdd;
                if(peopleAdd%m==0){
                    serverAdd = peopleAdd/m;
                }else{
                    serverAdd = (peopleAdd+(m-peopleAdd%m))/m;
                }
                
                for(int j=0;j<serverAdd;j++){
                    pq.offer(i+k-1);
                    answer++;
                }
            }
            
            while(!pq.isEmpty()){
                if(pq.peek()==i){
                    pq.poll();
                }else{
                    break;
                }
            }
        }
        return answer;
    }
}