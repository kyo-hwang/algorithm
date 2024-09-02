import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        Deque<Integer> deliveriesQue = new ArrayDeque<Integer>();
        Deque<Integer> pickupsQue = new ArrayDeque<Integer>();
        
        for(int i=0;i<n;i++){
            deliveriesQue.push(deliveries[i]);
        }
        
        for(int i=0;i<n;i++){
            pickupsQue.push(pickups[i]);
        }
        
        while(!(deliveriesQue.isEmpty()&&pickupsQue.isEmpty())){
            while(!deliveriesQue.isEmpty()&&(deliveriesQue.peek()==0)){
                deliveriesQue.pop();
            }
            while(!pickupsQue.isEmpty()&&(pickupsQue.peek()==0)){
                pickupsQue.pop();
            }
            answer+=Math.max(deliveriesQue.size(),pickupsQue.size())*2;
            
            int deliverRemain = cap;
            int pickupRemain = cap;
            while(deliverRemain>0&&!deliveriesQue.isEmpty()){
                int lastDeliver = deliveriesQue.pop();
                if(deliverRemain<lastDeliver){
                    deliveriesQue.push(lastDeliver-deliverRemain);
                    deliverRemain = 0;
                }
                else{
                    deliverRemain -= lastDeliver;
                }
            }
            
            while(pickupRemain>0&&!pickupsQue.isEmpty()){
                int lastPickup = pickupsQue.pop();
                if(pickupRemain<lastPickup){
                    pickupsQue.push(lastPickup-pickupRemain);
                    pickupRemain = 0;
                }
                else{
                    pickupRemain -= lastPickup;
                }
            }
        }
        return answer;
    }
}