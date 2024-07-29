import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int row;
    public static int col;
    private static int[][] map;
    private static int[][] visited;
    private static int[] dRow = {0,1,0,-1};
    private static int[] dCol = {1,0,-1,0};

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] size = inputIntArray(bf);

        row = size[0];
        col = size[1];

        map = new int[row][col];
        for(int i=0;i<row;i++){
            map[i] = inputIntArray(bf);
        }

        initVisited();
        visited[row-1][col-1] = 1;

        dfs(0,0);
        System.out.println(visited[0][0]);
    }

    private static void dfs(int i,int j){
        visited[i][j] = 0;
        for(int k=0;k<4;k++){
            int ni = i+dRow[k], nj = j+dCol[k];
            if(ni>=0&&ni<row&&nj>=0&&nj<col){
                if(map[i][j]>map[ni][nj]){
                    if(visited[ni][nj]==-1){
                        dfs(ni,nj);
                    }
                    visited[i][j] += visited[ni][nj];
                }
            }
        }
    }

    private static int[] inputIntArray(BufferedReader bf) throws Exception{
        return Arrays.stream(bf.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static void initVisited(){
        visited = new int[row][col];
        for(int[] row : visited){
            Arrays.fill(row,-1);
        }
    }
}
