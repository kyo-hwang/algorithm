import java.util.*;
class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        answer = calPossibleCount(1,n,0,q,ans,new ArrayList<>());
        return answer;
    }
    
    public int calPossibleCount(int start, int lastNum,int depth,int[][] q,int[] ans,List<Integer> nums){
        if(depth==5){
            if(isPossibleNum(q,ans,nums)){
                return 1;
            }
            return 0;
        }
        if(start>lastNum){
            return 0;
        }
        int result =0;
        for(int i=start;i<=lastNum;i++){
            List<Integer> nextNums = new ArrayList<>(nums);
            nextNums.add(i);
            result +=calPossibleCount(i+1,lastNum,depth+1,q,ans,nextNums);
        }
        return result;
    }
    
    public boolean isPossibleNum(int[][] q,int[] ans,List<Integer> nums){
        for(int j=0;j<q.length;j++){
            int matchCount = 0;
            for(int i=0;i<5;i++){
                if(nums.contains(q[j][i])){
                    matchCount++;
                }
            }
            if(ans[j]!=matchCount){
                return false;
            }
        }
        return true;
    }
}