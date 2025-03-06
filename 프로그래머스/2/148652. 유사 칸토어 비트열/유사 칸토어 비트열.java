import java.util.*;
class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        answer= (int)calCount(n,l,r);
        return answer;
    }
    
    public long calCount(int n,long l,long r){
        // System.out.println(n+" "+l+" "+r);
        long result = 0;
        for(int i=0;i<5;i++){
            if(i==2){
                continue;
            }
            long start = (long)Math.pow(5,n-1)*(long)i+1L;
            long end = (long)Math.pow(5,n-1)*((long)i+1L);
            if(start<l&&end>=l){
                if(end>r){
                    result+=calCount(n-1,l-(start-1L),r-(start-1L));
                }else{
                    result+=calCount(n-1,l-(start-1L),end-(start-1L));
                }
            }else if(start>=l&&end<=r){
                result+=(long)Math.pow(4,n-1);
            }
            else if(start<=r&&end>r){
                // System.out.println(n+" "+l+" "+r+" "+start+" "+end);
                // System.out.println((start-(start-1))+" "+(r-(start-1)));
                // System.out.println("하이여");
                result+=calCount(n-1,start-(start-1L),r-(start-1L));
            }
        }
        return result;
    }
}