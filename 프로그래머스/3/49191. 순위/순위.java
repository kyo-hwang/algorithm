import java.io.*;
import java.util.*;
class Solution {
    public int solution(int n, int[][] results) {
        boolean[][] dp = new boolean[n+1][n+1];
        
        for(int[] result : results){
            dp[result[0]][result[1]] = true;
        }
        
        for(int i=1;i<n+1;i++){
            for(int j=1;j<n+1;j++){
                if(j==i){
                    continue;
                }
                for(int k=1;k<n+1;k++){
                    if(k==i||j==k){
                        continue;
                    }
                    dp[j][k] = dp[j][k]||(dp[j][i]&&dp[i][k]);
                }
            }
        }
        
        int answer=0;
        
        int sum = 0;
        for(int i=1;i<n+1;i++){
            for(int j=1;j<n+1;j++){
                if(dp[i][j]) sum++;
            }
            for(int j=1;j<n+1;j++){
                if(dp[j][i]) sum++;
            }
            if(sum==n-1) answer++;
            sum = 0;
        }
        return answer;
    }
}