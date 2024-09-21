import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main{
    static int row;
    static int col;
    static String[][] map;
    static int[] dRow = {0,1,0,-1};
    static int[] dCol = {1,0,-1,0};
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        row = input[0];
        col = input[1];

        map = new String[row][col];
        visited = new boolean[row][col];

        for(int i=0;i<row;i++){
            map[i] = bf.readLine().split("");
        }

        int result = 0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(!visited[i][j]){
                    result++;
                    dfs(i,j);
                }
            }
        }
        System.out.println(result);
    }

    public static void dfs(int cRow,int cCol){
        visited[cRow][cCol] = true;
        int nRow;
        int nCol;
        String d = map[cRow][cCol];
        if(d.equals("D")){
            nRow = cRow+1;
            nCol = cCol;
            if(check(nRow,nCol)) {
                dfs(nRow, nCol);
            }
        }
        if(d.equals("U")){
            nRow = cRow-1;
            nCol = cCol;
            if(check(nRow,nCol)){
                dfs(nRow,nCol);
            }
        }
        if(d.equals("L")){
            nRow = cRow;
            nCol = cCol-1;
            if(check(nRow,nCol)){
                dfs(nRow,nCol);
            }
        }
        if(d.equals("R")){
            nRow = cRow;
            nCol = cCol+1;
            if(check(nRow,nCol)){
                dfs(nRow,nCol);
            }
        }

        for(int i=0;i<4;i++){
            nRow = cRow+dRow[i];
            nCol = cCol+dCol[i];
            if(check(nRow,nCol)){
                if(i==0&&map[nRow][nCol].equals("L")){
                    dfs(nRow,nCol);
                }
                if(i==1&&map[nRow][nCol].equals("U")){
                    dfs(nRow,nCol);
                }
                if(i==2&&map[nRow][nCol].equals("R")){
                    dfs(nRow,nCol);
                }
                if(i==3&&map[nRow][nCol].equals("D")){
                    dfs(nRow,nCol);
                }
            }
        }
    }

    public static boolean check(int nRow,int nCol){
        if(nRow>=0&&nRow<row&&nCol<col&&nCol>=0&&(!visited[nRow][nCol])){
            return true;
        }
        return false;
    }
}