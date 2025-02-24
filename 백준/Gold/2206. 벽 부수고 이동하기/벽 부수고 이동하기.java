import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static int[] dRow = {0,1,0,-1};
    static int[] dCol = {1,0,-1,0};
    static int rs;
    static int cs;

    public static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rs = input[0];
        cs = input[1];

        map = new int[rs][cs];

        for(int i=0;i<rs;i++){
            map[i] = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        List<List<Integer>> walls = new ArrayList<>();
        for(int i=0;i<rs;i++){
            for(int j=0;j<cs;j++){
                if(map[i][j]==1){
                    walls.add(List.of(i,j));
                }
            }
        }

        int[][] distFromStart = calDist(0,0);
        int[][] distFromEnd = calDist(rs-1,cs-1);

        int minDist = distFromStart[rs-1][cs-1];
        for(List<Integer> wall:walls){
            int startFrom = 10000000;
            int endFrom = 10000000;
            for(int i=0;i<4;i++){
                int caR = wall.get(0)+dRow[i];
                int caC = wall.get(1)+dCol[i];
                if(caR<0||caR>=rs||caC<0||caC>=cs){
                    continue;
                }
                startFrom = Math.min(startFrom,distFromStart[caR][caC]);
                endFrom = Math.min(endFrom,distFromEnd[caR][caC]);
            }
            minDist = Math.min(minDist,startFrom+endFrom+1);
        }
        if(minDist==10000000){
            System.out.println(-1);
        }else{
            System.out.println(minDist);
        }
    }

    public static int[][] calDist(int sr,int sc){
        int[][] visited = new int[rs][cs];
        for(int i=0;i<rs;i++){
            Arrays.fill(visited[i],10000000);
        }
        Deque<List<Integer>> q = new ArrayDeque<>();
        q.offer(List.of(sr,sc,1));
        visited[sr][sc] = 1;

        while(!q.isEmpty()){
            List<Integer> bf = q.poll();
            int br = bf.get(0);
            int bc = bf.get(1);
            for(int i=0;i<4;i++){
                int cr = br+dRow[i];
                int cc = bc+dCol[i];
                if(cr<0||cr>=rs||cc<0||cc>=cs){
                    continue;
                }
                if(map[cr][cc]==1){
                    continue;
                }
                if(visited[cr][cc]!=10000000){
                    continue;
                }
                q.offer(List.of(cr,cc,bf.get(2)+1));
                visited[cr][cc] = bf.get(2)+1;
            }
        }
        return visited;
    }
}
