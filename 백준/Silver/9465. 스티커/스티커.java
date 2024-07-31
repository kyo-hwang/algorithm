import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());

        for(int i=0;i<t;i++){
            upSticker(bf);
        }
    }

    public static void upSticker(BufferedReader bf) throws Exception{
        int n = Integer.parseInt(bf.readLine());
        int[][] sticker = new int[2][n];
        for(int i=0;i<2;i++){
            sticker[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] dp = new int[2][n+1];

        dp[0][1] = sticker[0][0];
        dp[1][1] = sticker[1][0];

        for(int i=2;i<n+1;i++){
            dp[0][i] = Math.max(dp[1][i-1],dp[1][i-2])+sticker[0][i-1];
            dp[1][i] = Math.max(dp[0][i-1],dp[0][i-2])+sticker[1][i-1];
        }

        System.out.println(Math.max(dp[0][n],dp[1][n]));
    }
}
