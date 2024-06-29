import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

        List<Integer> box = Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        Integer[] dp = new Integer[n];
        dp[0] = 1;

        for(int i=0;i<n;i++){
            int max = 1;
            for(int j=0;j<i;j++){
                if(box.get(i)>box.get(j)){
                    max = Integer.max(max,dp[j]+1);
                }
                dp[i] = max;
            }
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.stream(dp).max(Integer::compareTo).get());
    }
}
