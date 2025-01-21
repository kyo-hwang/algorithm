import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int row;
    public static int col;
    public static int damage;
    public static int[][] map;

    public static int[] dRow = {0,1,0,-1};
    public static int[] dCol = {1,0,-1,0};

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        row = input[0];
        col = input[1];
        damage = input[2];

        map = new int[row][col];
        for(int i=0;i<row;i++){
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int result = 0;
        for(int i=0;i<col;i++){
            for(int j=i+1;j<col;j++){
                for(int k=j+1;k<col;k++){
                    result = Math.max(result,calEnemyKilled(List.of(i,j,k)));
                }
            }
        }

        System.out.println(result);
    }

    public static int calEnemyKilled(List<Integer> attackers){
        int[][] curMap = new int[row][col];
        for(int i=0;i<row;i++){
            curMap[i] = Arrays.copyOf(map[i],col);
        }
        int result = 0;

        for(int i=0;i<row;i++){
            Set<List<Integer>> enemyKilledLoc = new HashSet<>();
            for(Integer attacker:attackers){
                List<List<Integer>> enemyKilledLocOfCurAttacker = new ArrayList<>();
                Deque<List<Integer>> q = new ArrayDeque<>();
                if(1<=damage&&curMap[row-1][attacker]==1){
                    enemyKilledLocOfCurAttacker.add(List.of(row-1,attacker,1));
                }
                q.offer(List.of(row-1,attacker,1));
                boolean[][] visited = new boolean[row][col];
                visited[row-1][attacker] = true;
                while(!q.isEmpty()){
                    List<Integer> beforeLoc = q.poll();
                    for(int j=0;j<4;j++){
                        int curRow = beforeLoc.get(0)+dRow[j];
                        int curCol = beforeLoc.get(1)+dCol[j];
                        int curDistance = beforeLoc.get(2)+1;
                        if(curDistance>damage){
                            continue;
                        }
                        if(curRow<0||curRow>=row||curCol<0||curCol>=col){
                            continue;
                        }
                        if(visited[curRow][curCol]){
                            continue;
                        }
                        if(curMap[curRow][curCol]==1){
                            enemyKilledLocOfCurAttacker.add(List.of(curRow,curCol,curDistance));
                        }
                        q.offer(List.of(curRow,curCol,curDistance));
                        visited[curRow][curCol] = true;
                    }
                }
                if(enemyKilledLocOfCurAttacker.isEmpty()){
                    continue;
                }
                int curDistance = Integer.MAX_VALUE;
                int curRow = Integer.MAX_VALUE;
                int curCol = Integer.MAX_VALUE;
                for(List<Integer> enemy:enemyKilledLocOfCurAttacker){
                    if(enemy.get(2)<curDistance){
                        curDistance = enemy.get(2);
                        curRow = enemy.get(0);
                        curCol = enemy.get(1);
                    }else if(enemy.get(2)==curDistance){
                         if(enemy.get(1)<curCol){
                             curDistance = enemy.get(2);
                             curRow = enemy.get(0);
                             curCol = enemy.get(1);
                         }
                    }
                }
                enemyKilledLoc.add(List.of(curRow,curCol));
            }
            result += enemyKilledLoc.size();
            for(List<Integer> enemy:enemyKilledLoc){
                curMap[enemy.get(0)][enemy.get(1)] = 0;
            }
            for(int j=row-1;j>0;j--){
                curMap[j] = curMap[j-1];
            }
            curMap[0] = new int[col];
        }
        return result;
    }

}
