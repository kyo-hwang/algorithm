import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        int[] numLine = Arrays.stream(bf.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] dp = new int[n+1][n];
        Arrays.fill(dp[1],1);

        for(int i=2;i<n+1;i++){
            for(int j=0;j<n-i+1;j++){
//                System.out.println(j);
                if(numLine[j]==numLine[j+i-1]){
                    dp[i][j] = dp[i-2][j+1]+2;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j+1]);
                }
            }
        }
        System.out.println(n-dp[n][0]);
    }
}
