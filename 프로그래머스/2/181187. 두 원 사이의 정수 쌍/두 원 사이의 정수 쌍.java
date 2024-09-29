import java.io.*;
class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        // double a = 3.5;
        // System.out.println(calPoint(r2));
        // System.out.println(calPoint(r1));
        answer = calPoint(r2)-calPoint(r1)+calMarginPoint(r1);
        return answer;
    }
    public long calPoint(int r){
        long oneSide = 0;
        for(long i=1;i<r;i++){
            oneSide+=(long)Math.sqrt((long)Math.pow(r,2)-(long)Math.pow(i,2));
        }
        return oneSide*4+r*4+1;
    }
    
    public long calMarginPoint(int r){
        long oneSide = 0;
        for(long i=1;i<r;i++){
            double c = Math.sqrt((long)Math.pow(r,2)-(long)Math.pow(i,2));
            if(Math.ceil(c)==c){
                oneSide++;
            }
        }
        return oneSide*4+4;
    }
}