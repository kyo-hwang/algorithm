import java.util.*;
class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        int length = weights.length;
        Map<Integer,Integer> map = new HashMap<>();
        
        for(int i=0;i<length;i++){
            if(map.containsKey(weights[i])){
                map.replace(weights[i],map.get(weights[i])+1);
            }
            else{
                map.put(weights[i],1);
            }
        }
        
        for(int key:map.keySet()){
            answer += ((long)map.get(key))*((long)map.get(key)-1)/2;
            
            if(map.containsKey(key*2)){
                answer += ((long)map.get(key*2))*(long)map.get(key);
            }
            
            if(key%3==0){
                if(map.containsKey(key/3*4)){
                    answer += ((long)map.get(key/3*4))*(long)map.get(key);
                }
            }
            
            if(key%2==0){
                if(map.containsKey(key/2*3)){
                    answer += ((long)map.get(key/2*3))*(long)map.get(key);
                }
            }
        }
    
        return answer;
    }
}