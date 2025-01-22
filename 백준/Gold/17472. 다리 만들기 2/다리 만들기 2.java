import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

public class Main{
    public static int row;
    public static int col;
    public static int[][] map;
    public static int[] dRow = {0,1,0,-1};
    public static int[] dCol = {1,0,-1,0};
    public static int islandCount;
    public static int[][] distanceEachIsland;
    public static List<Set<Integer>> linesPossible;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        row = input[0];
        col = input[1];
        map = new int[row][col];
        for(int i=0;i<row;i++){
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        islandCount = designateMap();
        distanceEachIsland = new int[islandCount+1][islandCount+1];
        for(int i=0;i<islandCount+1;i++){
            Arrays.fill(distanceEachIsland[i],Integer.MAX_VALUE);
        }


        for(int i=0;i<row;i++){
            int start = Integer.MAX_VALUE-1;
            int beforeIsland = 0;
            for(int j=0;j<col;j++){
                if(map[i][j]!=0){
                    start = j;
                    beforeIsland = map[i][j];
                    break;
                }
            }
            int distance = 0;
            for(int j=start+1;j<col;j++){
                if(map[i][j]==0){
                    distance++;
                    continue;
                }
                if(map[i][j]!=beforeIsland){
                    if(distance>1){
                        int cur = map[i][j];
                        distanceEachIsland[cur][beforeIsland] = Math.min(distanceEachIsland[cur][beforeIsland],distance);
                        distanceEachIsland[beforeIsland][cur] = Math.min(distanceEachIsland[beforeIsland][cur],distance);
                    }
                    beforeIsland = map[i][j];
                }
                distance=0;
            }
        }
        for(int i=0;i<col;i++){
            int start = Integer.MAX_VALUE-1;
            int beforeIsland = 0;
            for(int j=0;j<row;j++){
                if(map[j][i]!=0){
                    start = j;
                    beforeIsland = map[j][i];
                    break;
                }
            }
            int distance=0;
            for(int j=start+1;j<row;j++){
                if(map[j][i]==0){
                    distance++;
                    continue;
                }
                if(map[j][i]!=beforeIsland){
                    int cur = map[j][i];
                    if(distance>1){
                        distanceEachIsland[cur][beforeIsland] = Math.min( distanceEachIsland[cur][beforeIsland],distance);
                        distanceEachIsland[beforeIsland][cur] = Math.min( distanceEachIsland[beforeIsland][cur],distance);
                    }
                    beforeIsland = map[j][i];
                }
                distance=0;
            }
        }

        Set<Set<Integer>> lines = new HashSet<>();
        for(int i=1;i<islandCount+1;i++){
            for(int j=1;j<islandCount+1;j++){
                if(distanceEachIsland[i][j]!=Integer.MAX_VALUE){
                    lines.add(new HashSet<>(List.of(i,j)));
                }
            }
        }

        linesPossible = new ArrayList<>(lines);
        int result = backtracking(0,new ArrayList<>());
        if(result==Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        System.out.println(result);
    }

    public static int backtracking(int depth,List<Set<Integer>> lines){
        if(depth>=linesPossible.size()){
            return Integer.MAX_VALUE;
        }
        int result = Integer.MAX_VALUE;
        List<Set<Integer>> curLines = new ArrayList<>(lines);
        curLines.add(linesPossible.get(depth));
        if(curLines.size()<islandCount-1){
            result = Math.min(result,backtracking(depth+1,curLines));
        }
        else{
            //모든 섬을 순회할 수 있는지 체크하고 비용계산
            result = Math.min(result,calLinesDistance(curLines));
        }
        result = Math.min(result,backtracking(depth+1,new ArrayList<>(lines)));
        return result;
    }

    public static int calLinesDistance(List<Set<Integer>> lines){
        boolean[][] doesLinesExists = new boolean[islandCount+1][islandCount+1];
        boolean[] visited = new boolean[islandCount+1];
        for(Set<Integer> line:lines){
            List<Integer> lineList = new ArrayList<>(line);
            doesLinesExists[lineList.get(0)][lineList.get(1)] = true;
            doesLinesExists[lineList.get(1)][lineList.get(0)] = true;
        }
        int distance = 0;
        Deque<Integer> que = new ArrayDeque<>();
        que.offer(1);
        visited[1]=true;
        while(!que.isEmpty()){
            int beforeIsLand = que.poll();
            for(int i=1;i<=islandCount;i++){
                if(visited[i]){
                    continue;
                }
                if(doesLinesExists[beforeIsLand][i]){
                    distance+=distanceEachIsland[beforeIsLand][i];
                    que.offer(i);
                    visited[i] = true;
                }
            }
        }
        for(int i=1;i<=islandCount;i++){
            if(!visited[i]){
                return Integer.MAX_VALUE;
            }
        }
        return distance;
    }

    public static int designateMap(){
        boolean[][] visited = new boolean[row][col];
        int name = 1;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(map[i][j]!=0&&!visited[i][j]){
                    Deque<List<Integer>> que = new ArrayDeque<>();
                    map[i][j] = name;
                    visited[i][j] = true;
                    que.offer(List.of(i,j));
                    while(!que.isEmpty()){
                        List<Integer> beforeLoc = que.poll();
                        for(int k=0;k<4;k++){
                            int curRow = beforeLoc.get(0)+dRow[k];
                            int curCol = beforeLoc.get(1)+dCol[k];
                            if(curRow<0||curRow>=row||curCol<0||curCol>=col){
                                continue;
                            }
                            if(visited[curRow][curCol]||map[curRow][curCol]==0){
                                continue;
                            }
                            map[curRow][curCol] = name;
                            visited[curRow][curCol] = true;
                            que.offer(List.of(curRow,curCol));
                        }
                    }
                    name++;
                }

            }
        }
        return name-1;
    }
}
