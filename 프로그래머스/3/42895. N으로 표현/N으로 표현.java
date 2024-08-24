import java.util.*;
import java.io.*;

class Solution {
    List<Set<Integer>> dp = new ArrayList<>();
    int N;
    
    public int solution(int N, int number) {
        int answer = -1;
        this.N = N;
        
        if(N==number){
            return 1;
        }
        
        dp.add(new HashSet());
        dp.add(new HashSet(Arrays.asList(calContinuousNum(N,1))));
        
        for(int i=2;i<=8;i++){
            if(calResultInAndFind(i,number)){
                System.out.println(dp);
                return i;
            }
        }
        
        return answer;
    }
    
    public boolean calResultInAndFind(int n,int numToFind){
        Set<Integer> results = new HashSet<>();
        int conNum = calContinuousNum(N,n);
        if(conNum==numToFind){
            return true;
        }
        results.add(conNum);
        
        for(int i=1;i<n;i++){
            for(int number1 : dp.get(i)){
                for(int number2 : dp.get(n-i)){
                    int numPlus = number1+number2;
                    if(numPlus==numToFind){
                        return true;
                    }
                    if(numPlus!=0){
                        results.add(numPlus);
                    }
                    
                    int numSub = number1-number2;
                    if(numSub==numToFind){
                        return true;
                    }
                    if(numSub!=0){
                        results.add(numSub);
                    }
                    
                    int numMul = number1*number2;
                    if(numMul==numToFind){
                        return true;
                    }
                    if(numMul!=0){
                        results.add(numMul);
                    }
                    
                    int numDivide = number1/number2;
                    if(numDivide==numToFind){
                        return true;
                    }
                    if(numDivide!=0){
                        results.add(numDivide);
                    }
                }
            }
        }
        dp.add(results);
        return false;
    }
    
    public int calContinuousNum(int N,int length){
        int continuousNum = 0;
        for(int i=0;i<length;i++){
            continuousNum += N*Math.pow(10,i);
        }
        return continuousNum;
    }
}