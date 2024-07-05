import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static Integer[] dRow = {0,1,0,-1};
    private static Integer[] dCol = {1,0,-1,0};

    private static String[][] graph;
    private static int row;
    private static int col;
    private static int curMax = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] command = Arrays.stream(bf.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        row = command[0];
        col = command[1];

        graph = new String[row][col];
        for(int i=0;i<row;i++){
            graph[i] = bf.readLine().split("");
        }

        Boolean[][] visited = new Boolean[row][col];

        Set<String> log = new HashSet<>();
        backTracking(0,0,log);

        System.out.println(curMax);
    }
    public static void backTracking(int i ,int j ,Set<String> log){
        if(log.contains(graph[i][j])){
            return;
        }
        log.add(graph[i][j]);
        curMax = Math.max(log.size(),curMax);
        for(int k=0;k<4;k++){
            int nextRow = dRow[k]+i, nextCol = dCol[k]+j;
            if(nextRow<row && nextRow>=0 && nextCol<col && nextCol>=0){
                backTracking(nextRow,nextCol,log);
            }
        }
        log.remove(graph[i][j]);
    }
}
