import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        int[] numLine = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[n];
        Arrays.fill(dp,Integer.MAX_VALUE);

        int maxLoc = -1;

        for(int num:numLine){
//            System.out.println(Arrays.toString(dp));
            int loc = findLoc(num,dp);
            if(loc>maxLoc){
                maxLoc = loc;
            }
            dp[loc] = num;
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(maxLoc+1);
    }
    public static int findLoc(int num,int[] dp){
        int left = 0;
        int right = dp.length-1;

        while(right>=left) {
            int mid = (left + right) / 2;
            if (dp[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
