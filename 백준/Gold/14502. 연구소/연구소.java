import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Main{
    public static int rs;
    public static int cs;

    public static Integer[][] map;

    public static int[] dRow = {0,1,0,-1};
    public static int[] dCol = {1,0,-1,0};

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        Integer[] input = Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        rs = input[0];
        cs = input[1];

        map = new Integer[rs][cs];
        for(int i=0;i<rs;i++){
            map[i] = Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        }

        int result = 0;

        for(int i=0;i<rs*cs;i++){
            for(int j=i+1;j<rs*cs;j++){
                for(int k=j+1;k<rs*cs;k++){
                    int row1 = i/cs;
                    int col1 = i%cs;
                    int row2 = j/cs;
                    int col2 = j%cs;
                    int row3 = k/cs;
                    int col3 = k%cs;
                    if(map[row1][col1]==2||map[row1][col1]==1){
                        continue;
                    }
                    if(map[row2][col2]==2||map[row2][col2]==1){
                        continue;
                    }
                    if(map[row3][col3]==2||map[row3][col3]==1){
                        continue;
                    }
                    Integer[][] customMap = new Integer[rs][cs];
                    for(int r=0;r<rs;r++){
                        customMap[r] = Arrays.copyOf(map[r],cs);
                    }
                    customMap[row1][col1] = 1;
                    customMap[row2][col2] = 1;
                    customMap[row3][col3] = 1;
                    result = Math.max(result,calSafeArea(customMap));
                }
            }
        }
        System.out.println(result);
    }

    public static int calSafeArea(Integer[][] map){
        boolean[][] visited = new boolean[rs][cs];
        for(int i=0;i<rs;i++){
            for(int j=0;j<cs;j++){
                if(map[i][j]==2&&!visited[i][j]){
                    bfs(i,j,map,visited);
                }
            }
        }
        int result = 0;
        for(int i=0;i<rs;i++){
            for(int j=0;j<cs;j++){
                if(map[i][j]==0){
                    result++;
                }
            }
        }
        return result;
    }

    public static void bfs(int row,int col,Integer[][] map,boolean[][] visited){
        Deque<List<Integer>> q = new ArrayDeque<>();
        q.offer(List.of(row,col));
        visited[row][col] = true;
        map[row][col] = 2;
        while(!q.isEmpty()){
            List<Integer> beforeInfo = q.poll();
            int br = beforeInfo.get(0);
            int bc = beforeInfo.get(1);
            for(int i=0;i<4;i++){
                int cr = br+dRow[i];
                int cc = bc+dCol[i];
                if(cr<0||cr>=rs||cc<0||cc>=cs){
                    continue;
                }
                if(map[cr][cc]==1){
                    continue;
                }
                if(visited[cr][cc]){
                    continue;
                }
                map[cr][cc] = 2;
                q.offer(List.of(cr,cc));
                visited[cr][cc] = true;
            }
        }
    }
}
