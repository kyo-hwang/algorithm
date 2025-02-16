import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Main{
    public static int buildingCount;
    public static int leftCount;
    public static int rightCount;
    public static int modNum = 1000000007;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        buildingCount = input[0];
        leftCount = input[1];
        rightCount = input[2];
        if(buildingCount==1){
            System.out.println(1);
            return;
        }

        long[][] dp = new long[buildingCount][buildingCount];
        dp[1][1] = 1;
        dp[0][0] = 1;

        for(int i=1;i<buildingCount;i++){
            for(int j=1;j<buildingCount;j++){
                dp[i][j] = (dp[i-1][j-1]+(dp[i-1][j]*(i-1)%modNum))%modNum;
            }
        }

        BigInteger[][] combinationDp = new BigInteger[buildingCount][buildingCount];
        for(int i=0;i<buildingCount;i++){
            for(int j=0;j<buildingCount;j++){
                combinationDp[i][j] = new BigInteger("0");
            }
        }
        for(int i=0;i<buildingCount;i++){
            combinationDp[i][0] = new BigInteger("1");
        }

        for(int i=1;i<buildingCount;i++){
            for(int j=1;j<buildingCount;j++){
                combinationDp[i][j] = combinationDp[i-1][j-1].multiply(new BigInteger(Integer.toString(i))).divide(new BigInteger(Integer.toString(j)));
            }
        }

//        for(int i=0;i<buildingCount;i++){
//            System.out.println(Arrays.toString(combinationDp[i]));
//        }
//        System.out.println();


        long result = 0;

        for(int i=0;i<buildingCount;i++){
            result += (dp[i][leftCount-1]*dp[buildingCount-1-i][rightCount-1])%modNum
                    *(combinationDp[buildingCount-1][i].mod(new BigInteger(Integer.toString(modNum))).longValue())%modNum;
            result%=modNum;
        }

        System.out.println(result);
    }
}
