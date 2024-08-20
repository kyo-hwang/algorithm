import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

public class Main {

    static int row;
    static int col;
    static String[][] board;
    static int[][] dp;

    static boolean[][] visited;

    static int[] dRow = {0,1,0,-1};
    static int[] dCol = {1,0,-1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] commands = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        row = commands[0];
        col = commands[1];
        board = new String[row][col];

        for(int i=0;i<row;i++){
            board[i] = bf.readLine().split("");
        }

        dp = new int[row][col];

        for(int i=0;i<row;i++){
            Arrays.fill(dp[i],-1);
        }

        visited = new boolean[row][col];
        System.out.println(dfs(0,0));
    }

    public static int dfs(int fromRow,int fromCol){
        visited[fromRow][fromCol] = true;
        int maxPlay = 1;
        for(int i=0;i<4;i++){
            int nextRow = fromRow+dRow[i]*Integer.parseInt(board[fromRow][fromCol]);
            int nextCol = fromCol+dCol[i]*Integer.parseInt(board[fromRow][fromCol]);
            if(nextRow>=0&&nextRow<row&&nextCol>=0&&nextCol<col&&!board[nextRow][nextCol].equals("H")){
                if(visited[nextRow][nextCol]){
                    System.out.println(-1);
                    System.exit(0);
                }
                if(dp[nextRow][nextCol]!=-1){
                    maxPlay = Math.max(dp[nextRow][nextCol]+1,maxPlay);
                }
                else{
                    maxPlay = Math.max(dfs(nextRow,nextCol)+1,maxPlay);
                }
            }
        }
        visited[fromRow][fromCol] = false;
        dp[fromRow][fromCol] = maxPlay;
        return maxPlay;
    }

}
