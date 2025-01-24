import javax.lang.model.type.ArrayType;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static int[] dRow = {0,1,0,-1};
    public static int[] dCol = {1,0,-1,0};
    public static int size;
    public static int countToActivate;
    public static int[][] map;
    public static int emptyCount = 0;
    public static List<List<Integer>> virusLocs = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        size = input[0];
        countToActivate = input[1];
        map=new int[size][size];
        for(int i=0;i<size;i++){
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(map[i][j]==2){
                    virusLocs.add(List.of(i,j));
                }
                else if(map[i][j]==0){
                    emptyCount++;
                }
            }
        }

        int result = back(0,0,new ArrayList<>());
        if(result==Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        System.out.println(result);
    }

    public static int back(int depth,int count,List<Integer> locs){
        if(count==countToActivate){
            List<List<Integer>> candidate = new ArrayList<>();
            for(int loc:locs){
                candidate.add(virusLocs.get(loc));
            }
            return bfs(candidate);
        }
        if(depth>=virusLocs.size()){
            return Integer.MAX_VALUE;
        }
        int result = Integer.MAX_VALUE;
        List<Integer> newLocs = new ArrayList<>(locs);
        newLocs.add(depth);
        result = Math.min(result,back(depth+1,count+1,newLocs));
        result = Math.min(result,back(depth+1,count,new ArrayList<>(locs)));
        return result;
    }

    public static int bfs(List<List<Integer>> virusActivatingLocs){
        int[][] simulationMap = new int[size][size];
        int remainCount = emptyCount;
        for(int i=0;i<size;i++){
            simulationMap[i] = Arrays.copyOf(map[i],map[i].length);
        }

        Deque<List<Integer>> que = new ArrayDeque<>();
        for(List<Integer> loc:virusActivatingLocs){
            que.offer(loc);
            simulationMap[loc.get(0)][loc.get(1)] = 3;
        }
        if(remainCount==0){
            return 0;
        }
        Deque<List<Integer>> tempQue;
        int time = 0;
        while(true){
            tempQue = new ArrayDeque<>();
            while(!que.isEmpty()){
                List<Integer> curLoc = que.poll();
                for(int i=0;i<4;i++){
                    int curRow = curLoc.get(0)+dRow[i];
                    int curCol = curLoc.get(1)+dCol[i];
                    if(curRow<0||curRow>=size||curCol<0||curCol>=size){
                        continue;
                    }
                    if(simulationMap[curRow][curCol]==1||simulationMap[curRow][curCol]==3){
                        continue;
                    }
                    if(simulationMap[curRow][curCol]==0){
                        remainCount--;
                    }
                    simulationMap[curRow][curCol] =3;
                    tempQue.add(List.of(curRow,curCol));
                }
            }
            if(remainCount==0){
                time++;
                break;
            }
            if(tempQue.isEmpty()){
                break;
            }
            que = tempQue;
            time++;
        }
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(simulationMap[i][j]==0){
                    return Integer.MAX_VALUE;
                }
            }
        }
        return time;
    }
}
