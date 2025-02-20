import java.util.*;
class Solution {
    public int solution(int[][] info, int n, int m) {
        int answer = 0;
        int[][] dp = new int[info.length][m];
        for(int i=0;i<info[0][1];i++){
            if(i>=m){
                break;
            }
            dp[0][i] = info[0][0];
        }
        
        for(int i=1;i<info.length;i++){
            for(int j=0;j<m;j++){
                int aStill = dp[i-1][j]+info[i][0];
                int bStill = Integer.MAX_VALUE;
                if(j-info[i][1]>=0){
                    bStill = dp[i-1][j-info[i][1]];
                }
                dp[i][j] = Math.min(aStill,bStill);
            }
        }
        for(int i=0;i<dp.length;i++){
            System.out.println(Arrays.toString(dp[i]));
        }
        
        if(dp[info.length-1][m-1]>=n){
            answer = -1;
        }else{
            answer = dp[info.length-1][m-1];
        }
        return answer;
    }
}