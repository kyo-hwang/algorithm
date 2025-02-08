import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static int n;
    public static int[][] forest;
    public static int[][] dp;
    public static int[] dRow = {0,1,0,-1};
    public static int[] dCol = {1,0,-1,0};
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        forest = new int[n][n];
        for(int i=0;i<n;i++){
            forest[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int result = 0;
        dp = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                result = Math.max(result,dfs(i,j));
            }
        }
        System.out.println(result);
    }

    public static int dfs(int startRow,int startCol){
        if(dp[startRow][startCol]!=0){
            return dp[startRow][startCol];
        }
        int maxCount = 0;
        for(int i=0;i<4;i++){
            int nextRow = startRow+dRow[i];
            int nextCol = startCol+dCol[i];
            if(nextCol<0||nextCol>=n||nextRow<0||nextRow>=n){
                continue;
            }
            if(forest[startRow][startCol]>=forest[nextRow][nextCol]){
                continue;
            }
            maxCount = Math.max(maxCount,dfs(nextRow,nextCol));
        }
        dp[startRow][startCol] = maxCount+1;
        return maxCount+1;
    }
}
