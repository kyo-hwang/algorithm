import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] circle = new int[100];
        for(int i=1;i<=n;i++){
            int[] line = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            circle[line[0]-1] = i;
            circle[line[1]-1] = i;
        }
        int[][] dp = new int[101][100];

        for(int i=2;i<=100;i++){ 
            for(int j=0;j+i-1<=99;j++){
                if(circle[j]!=0&&circle[j]==circle[j+i-1]){
                    dp[i][j] = dp[i-2][j+1]+1;
                }
                else{
                    for(int k=1;k<i;k++){
                        dp[i][j] = Math.max(dp[i][j],dp[k][j]+dp[i-k][j+k]);
                    }
                }
            }
        }
        System.out.println(dp[100][0]);
    }
}
