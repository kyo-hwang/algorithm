import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        int[] dp = new int[n+1];

        dp[0] = 1;
        dp[1] = 1;
//        dp[2] = 3;

        for(int i=2;i<n+1;i++){
            dp[i] = ((dp[i-2]%10007*2)%10007+dp[i-1]%10007)%10007;
        }

        System.out.println(dp[n]);
    }
}
