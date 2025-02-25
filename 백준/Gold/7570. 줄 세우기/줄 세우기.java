import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        Integer[] line = Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] dp = new Integer[n];
        Arrays.fill(dp,0);
        for(int i=0;i<n;i++){
            if((line[i]-2)==-1){
                dp[line[i]-1] = 1;
                continue;
            }
            dp[line[i]-1] = dp[line[i]-2]+1;
        }
        System.out.println(n-Arrays.stream(dp).max(Comparator.comparingInt(e->e)).get());
    }
}
