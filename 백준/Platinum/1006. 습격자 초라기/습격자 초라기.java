import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        for(int i=0;i<t;i++){
            int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = input[0];
            int w = input[1];
            int[][] pentagon = new int[2][n];
            pentagon[0] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pentagon[1] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if(n==1){
                if(pentagon[0][0]+pentagon[1][0]<=w){
                    System.out.println(1);
                }
                else{
                    System.out.println(2);
                }
                continue;
            }

            System.out.println(calMinPlatoon(n,w,pentagon));
        }
    }

    public static int calMinPlatoon(int n,int w,int[][] pentagon){
        int min = Integer.MAX_VALUE;
        min = Math.min(firstLastNotContain(n,w,pentagon),min);
        if(pentagon[0][0]+pentagon[0][n-1]<=w){
            min = Math.min(firstContain(n,w,pentagon),min);
        }
        if(pentagon[1][0]+pentagon[1][n-1]<=w){
            min = Math.min(secondContain(n,w,pentagon),min);
        }
        if(pentagon[0][0]+pentagon[0][n-1]<=w&&pentagon[1][0]+pentagon[1][n-1]<=w){
            min = Math.min(allContain(n,w,pentagon),min);
        }
        return min;
    }
    public static int allContain(int n,int w,int[][] pentagon){
        int[][] dp = new int[4][n];
        if(pentagon[0][1]+pentagon[1][1]<=w){
            dp[0][1] = 1;
        }
        else{
            dp[0][1] = 2;
        }
        dp[1][1] = 2;
        dp[2][1] = 2;
        dp[3][1] = 2;

        for(int i=2;i<n-1;i++){
            int minVal = getDpMinVal(dp,i-1);
            dp[3][i] = minVal+2;

            int minUp;
            if(pentagon[0][i]+pentagon[0][i-1]<=w){
                minUp = Math.min(dp[2][i-1]+1,minVal+2);
            }
            else{
                minUp = minVal+2;
            }
            dp[1][i] = minUp;

            int minDown;
            if(pentagon[1][i]+pentagon[1][i-1]<=w){
                minDown = Math.min(dp[1][i-1]+1,minVal+2);
            }
            else{
                minDown = minVal+2;
            }
            dp[2][i] = minDown;


            int minAll = Integer.MAX_VALUE;
            if(pentagon[0][i]+pentagon[0][i-1]<=w && pentagon[1][i]+pentagon[1][i-1]<=w){
                minAll = dp[3][i-1];
            }
            if(pentagon[0][i]+pentagon[1][i]<=w){
                minAll = Math.min(minAll,minVal+1);
            }
            minAll = Math.min(minAll,minUp);
            minAll = Math.min(minAll,minDown);
            dp[0][i] = minAll;
        }
//        System.out.println("all");
//        for(int i=0;i<4;i++){
//            System.out.println(Arrays.toString(dp[i]));
//        }
//        System.out.println();
        return getDpMinVal(dp,n-2)+2;
    }
    public static int secondContain(int n,int w,int[][] pentagon){
        int[][] dp = new int[4][n];
        dp[0][0] = 2;
        dp[1][0] = Integer.MAX_VALUE-2;
        dp[2][0] = 2;
        dp[3][0] = Integer.MAX_VALUE-2;

        for(int i=1;i<n-1;i++){
            int minVal = getDpMinVal(dp,i-1);
            dp[3][i] = minVal+2;

            int minUp = Integer.MIN_VALUE;
            if(pentagon[0][i]+pentagon[0][i-1]<=w){
                minUp = Math.min(dp[2][i-1]+1,minVal+2);
            }
            else{
                minUp = minVal+2;
            }
            dp[1][i] = minUp;

            int minDown = Integer.MAX_VALUE;
            if(pentagon[1][i]+pentagon[1][i-1]<=w){
                minDown = Math.min(dp[1][i-1]+1,minVal+2);
            }
            else{
                minDown = minVal+2;
            }
            dp[2][i] = minDown;


            int minAll = Integer.MAX_VALUE;
            if(pentagon[0][i]+pentagon[0][i-1]<=w&&pentagon[1][i]+pentagon[1][i-1]<=w){
                minAll = dp[3][i-1];
            }
            if(pentagon[0][i]+pentagon[1][i]<=w){
                minAll = Math.min(minAll,minVal+1);
            }
            minAll = Math.min(minAll,minUp);
            minAll = Math.min(minAll,minDown);
            dp[0][i] = minAll;
        }
//        System.out.println("secondContain");
//        for(int i=0;i<4;i++){
//            System.out.println(Arrays.toString(dp[i]));
//        }
//        System.out.println();
        int minVal;
        if(pentagon[0][n-2]+pentagon[0][n-1]<=w){
            minVal = Math.min(getDpMinVal(dp,n-2)+1,dp[2][n-2]);
        }
        else{
            minVal = getDpMinVal(dp,n-2)+1;
        }
        return minVal;
    }
    public static int firstContain(int n,int w,int[][] pentagon){
        int[][] dp = new int[4][n];
        dp[0][0] = 2;
        dp[1][0] = 2;
        dp[2][0] = Integer.MAX_VALUE-2;
        dp[3][0] = Integer.MAX_VALUE-2;

        for(int i=1;i<n-1;i++){
            int minVal = getDpMinVal(dp,i-1);
            dp[3][i] = minVal+2;

            int minUp = Integer.MIN_VALUE;
            if(pentagon[0][i]+pentagon[0][i-1]<=w){
                minUp = Math.min(dp[2][i-1]+1,minVal+2);
            }
            else{
                minUp = minVal+2;
            }
            dp[1][i] = minUp;

            int minDown = Integer.MAX_VALUE;
            if(pentagon[1][i]+pentagon[1][i-1]<=w){
                minDown = Math.min(dp[1][i-1]+1,minVal+2);
            }
            else{
                minDown = minVal+2;
            }
            dp[2][i] = minDown;


            int minAll = Integer.MAX_VALUE;
            if(pentagon[0][i]+pentagon[0][i-1]<=w&&pentagon[1][i]+pentagon[1][i-1]<=w){
                minAll = dp[3][i-1];
            }
            if(pentagon[0][i]+pentagon[1][i]<=w){
                minAll = Math.min(minAll,minVal+1);
            }
            minAll = Math.min(minAll,minUp);
            minAll = Math.min(minAll,minDown);
            dp[0][i] = minAll;
        }
//        System.out.println("firstContain");
//        for(int i=0;i<4;i++){
//            System.out.println(Arrays.toString(dp[i]));
//        }
//        System.out.println();
        int minVal;
        if(pentagon[1][n-2]+pentagon[1][n-1]<=w){
            minVal = Math.min(getDpMinVal(dp,n-2)+1,dp[1][n-2]);
        }
        else{
            minVal = getDpMinVal(dp,n-2)+1;
        }
        return minVal;
    }

    public static int firstLastNotContain(int n,int w,int[][] pentagon){
        int[][] dp = new int[4][n];
        if(pentagon[0][0]+pentagon[1][0]<=w){
            dp[0][0] = 1;
        }
        else{
            dp[0][0] = 2;
        }
        dp[1][0] = 2;
        dp[2][0] = 2;
        dp[3][0] = 2;

        for(int i=1;i<n;i++){
            int minVal = getDpMinVal(dp,i-1);
            dp[3][i] = minVal+2;

            int minUp = Integer.MIN_VALUE;
            if(pentagon[0][i]+pentagon[0][i-1]<=w){
                minUp = Math.min(dp[2][i-1]+1,minVal+2);
            }
            else{
                minUp = minVal+2;
            }
            dp[1][i] = minUp;

            int minDown = Integer.MAX_VALUE;
            if(pentagon[1][i]+pentagon[1][i-1]<=w){
                minDown = Math.min(dp[1][i-1]+1,minVal+2);
            }
            else{
                minDown = minVal+2;
            }
            dp[2][i] = minDown;


            int minAll = Integer.MAX_VALUE;
            if(pentagon[0][i]+pentagon[0][i-1]<=w&&pentagon[1][i]+pentagon[1][i-1]<=w){
                minAll = dp[3][i-1];
            }
            if(pentagon[0][i]+pentagon[1][i]<=w){
                minAll = Math.min(minAll,minVal+1);
            }
            minAll = Math.min(minAll,minUp);
            minAll = Math.min(minAll,minDown);
            dp[0][i] = minAll;
        }
//        System.out.println("notContain");
//        for(int i=0;i<4;i++){
//            System.out.println(Arrays.toString(dp[i]));
//        }
//        System.out.println();
        return getDpMinVal(dp,n-1);
    }

    public static int getDpMinVal(int[][] dp,int col){
        int min = Integer.MAX_VALUE;
        for(int i=0;i<4;i++){
            min = Math.min(dp[i][col],min);
        }
        return min;
    }

}
