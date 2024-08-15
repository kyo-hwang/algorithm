import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        if(n==1){
            System.out.println(1);
            return;
        }

        int[][] dp = new int[n+1][n+1];
        dp[2][1] = 1;
        for(int i=3;i<n+1;i++){
            for(int j=1;j<i;j++){
                for(int k=i-j;k>0;k--){
                    dp[i][j] = (dp[i-1][k]%10000000+dp[i][j]%1000000)%1000000;
                }
            }
        }
        int result = 0;
        for(int i=1;i<n+1;i++){
            result =(dp[n][i]%10000000+result%10000000)%1000000;
        }
        System.out.println(result*2%1000000);
    }
}
