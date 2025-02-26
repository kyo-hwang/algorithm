import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int rs;
    public static int cs;

    public static int[][] map;

    public static int[] dRow = {0,1,0,-1};
    public static int[] dCol = {1,0,-1,0};
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rs = input[0];
        cs = input[1];

        map = new int[rs][cs];
        for(int i=0;i<rs;i++){
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        List<Integer> cheeseCount = new ArrayList<>();

        int time=0;
        while(true){
            cheeseCount.add(calCheese());
            if(cheeseCount.get(cheeseCount.size()-1)==0){
                break;
            }
            time++;
            bfs();
        }
        
        System.out.println(time);
        System.out.println(cheeseCount.get(Math.max(time-1,0)));

    }

    public static int bfs(){
        boolean[][] visited = new boolean[rs][cs];
        Deque<List<Integer>> q = new ArrayDeque<>();
        q.offer(List.of(0,0));
        while(!q.isEmpty()){
            List<Integer> bi = q.poll();
            for(int i=0;i<4;i++){
                int cr = bi.get(0)+dRow[i];
                int cc = bi.get(1)+dCol[i];
                if(cr<0||cr>=rs||cc<0||cc>=cs){
                    continue;
                }
                if(map[cr][cc]==1){
                    map[cr][cc]=0;
                    visited[cr][cc] = true;
                    continue;
                }
                if(visited[cr][cc]){
                    continue;
                }
                q.offer(List.of(cr,cc));
                visited[cr][cc]=true;
            }
        }
        
        return calCheese();
    }

    public static int calCheese(){
        int result = 0;
        for(int i=0;i<rs;i++){
            for(int j=0;j<cs;j++){
                if(map[i][j]==1){
                    result++;
                }
            }
        }
        return result;
    }

}
