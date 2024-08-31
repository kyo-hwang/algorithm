import java.io.*;
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long maxTotalTime = 1000000000000000000l;
        System.out.println(maxTotalTime);
        long minTotalTime = 1l;
        
        while(minTotalTime<maxTotalTime){
            long mid = (minTotalTime+maxTotalTime)/2;
            long availablePeople = getAvailablePeople(mid,times);
            if(n<=availablePeople){
                maxTotalTime = mid;
            }
            else{
                minTotalTime = mid+1;
            }
        }
        
        
        answer = maxTotalTime;
        
        return answer;
    }
    
    //특정 시간 동안 심사 가능한 최대 인원을 구한다.
    public long getAvailablePeople(long totalTime,int[] times){
        long availNum = 0l;
        for(int time:times){
            availNum += totalTime/time;
        }
        return availNum;
    }
}