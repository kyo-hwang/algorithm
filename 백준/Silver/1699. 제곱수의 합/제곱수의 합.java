import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

        List<Integer> numPossible = new ArrayList<>();
        for(int i=1;i<=Math.sqrt(n);i++){
            numPossible.add((int)Math.pow(i,2));
        }

        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);

        dp[1] = 1;
        dp[0] = 0;
        for(int i=2;i<=n;i++){
            for(int num:numPossible){
                if(num>i){
                    break;
                }
                dp[i] = Math.min(dp[i],dp[i-num]+1);
            }
        }
        
        System.out.println(dp[n]);
    }
}
