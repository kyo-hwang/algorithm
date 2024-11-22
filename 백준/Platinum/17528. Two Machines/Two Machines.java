import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] works = new int[n][2];
        for(int i=0;i<n;i++){
            works[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int[][] dp = new int[n+1][n*250+1];
        for(int i=1;i<=n;i++){
            int a = works[i-1][0];
            int b = works[i-1][1];
            for(int j=0;j<=n*250;j++){
                if(j>=a){
                    dp[i][j] = Math.min(dp[i-1][j-a],dp[i-1][j]+b);
                }
                else{
                    dp[i][j] = dp[i-1][j]+b;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i=0;i<=n*250;i++){
            int tmpMin = Math.max(i,dp[dp.length-1][i]);
            min = Math.min(min,tmpMin);
        }
        System.out.println(min);
    }
}
