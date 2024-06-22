import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static Integer[] dRow = {0,1,0,-1};
    public static Integer[] dCol = {1,0,-1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> commands = Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int n = commands.get(0);
        int m = commands.get(1);

        char[][] graph = new char[m][n];

        for(int i=0;i<m;i++){
            String command = bf.readLine();
            for(int j=0;j<n;j++){
                graph[i][j] = command.charAt(j);
            }
        }
        boolean[][] visited = new boolean[m][n];

        char current;
        int p;
        int wR = 0;
        int bR = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(visited[i][j]){
                    continue;
                }
                p = 0;
                current = graph[i][j];
                visited[i][j] = true;
                Deque<List<Integer>> que = new ArrayDeque<>();
                que.offer(Arrays.asList(i,j));
                p+=1;
                while(!que.isEmpty()){
                    List<Integer> loc = que.poll();
                    for(int k=0;k<4;k++){
                        int nRow = loc.get(0)+dRow[k];
                        int nCol = loc.get(1)+dCol[k];
                        if(nRow<m && nRow>=0 && nCol<n && nCol>=0){
                            if(current==graph[nRow][nCol] && !visited[nRow][nCol]){
                                visited[nRow][nCol] = true;
                                p+=1;
                                que.offer(Arrays.asList(nRow,nCol));
                            }
                        }
                    }
                }
                if(current == 'W'){
                    wR += Math.pow(p,2);
                }
                else{
                    bR += Math.pow(p,2);
                }
            }
        }
        System.out.println(wR);
        System.out.println(bR);
    }
}
