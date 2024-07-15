import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main{
    public static int max=0;
    static int[] dRow = {0,1,0,-1};
    static int[] dCol = {1,0,-1,0};
    static int row;
    static int col;

    static boolean[][] visited;
    static int[][] paper;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] command = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        row = command[0];
        col = command[1];

        paper = new int[row][col];
        for(int i=0;i<row;i++){
            paper[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        visited = new boolean[row][col];
//        for(int i=0;i<row;i++){
//            System.out.println(Arrays.toString(paper[i]));
//        }
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                explorerNode(i,j,1,0);
                explore(i,j);
            }
        }

        System.out.println(max);
    }

    public static void explorerNode(int i,int j,int depth,int total){
        total += paper[i][j];
        visited[i][j] = true;
        if(depth==4){
            max = Math.max(max,total);
            visited[i][j] = false;
            return;
        }
        for(int k=0;k<4;k++){
            int nRow = i+dRow[k];
            int nCol = j+dCol[k];
            if(nRow>=0 && nRow<row && nCol>=0 && nCol<col){
                if(!visited[nRow][nCol]){
                    explorerNode(nRow,nCol,depth+1,total);
                }
            }
        }
        visited[i][j] = false;
    }

    public static void explore(int i,int j){
        List<Integer> possible = new ArrayList<>();
        for(int k=0;k<4;k++){
            int nRow = i+dRow[k];
            int nCol = j+dCol[k];
            if(nRow>=0 && nRow<row && nCol>=0 && nCol<col){
                possible.add(paper[nRow][nCol]);
            }
        }
        int sum = possible.stream().mapToInt(e->e).sum()+paper[i][j];
        if(possible.size()==4){
            for(int m=0;m<4;m++){
                int nRow = i+dRow[m];
                int nCol = j+dCol[m];
//                System.out.println(i+" "+j+" "+(sum-paper[nRow][nCol]));
                max = Math.max(max,sum-paper[nRow][nCol]);
            }
        }
        if(possible.size()==3){
            max = Math.max(max,sum);
        }
    }
}
