import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int startRow;
    public static int startCol;
    public static int[] dRow = {0,1,1,1,0,-1,-1,-1};
    public static int[] dCol = {1,1,0,-1,-1,-1,0,1};
    public static List<List<Integer>> destinations;
    public static int[][] heights;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        destinations = new ArrayList<>();
        for(int i=0;i<n;i++){
            String[] input = bf.readLine().split("");
            for(int j=0;j<n;j++){
                if(input[j].equals("P")){
                    startRow = i;
                    startCol = j;
                }else if(input[j].equals("K")){
                    destinations.add(Arrays.asList(i,j));
                }
            }
        }

        heights = new int[n][n];
        for(int i=0;i<n;i++){
            heights[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Set<Integer> heightsExists = new HashSet<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                heightsExists.add(heights[i][j]);
            }
        }

        List<Integer> heightsSorted = new ArrayList<>(heightsExists);
        heightsSorted.sort(Comparator.comparingInt(e->e));

        int result = Integer.MAX_VALUE;
        for(int minIndex=0;minIndex<=heightsSorted.size()-1;minIndex++){
            int left = minIndex;
            int right = heightsSorted.size()-1;
            while(left<=right){
                int mid = (left+right)/2;
                if(isPossible(heightsSorted.get(minIndex),heightsSorted.get(mid))){
                    right = mid-1;
                }else{
                    left = mid+1;
                }
            }
            if(left>heightsSorted.size()-1){
                continue;
            }
            result = Math.min(result,heightsSorted.get(left)-heightsSorted.get(minIndex));
        }

        System.out.println(result);
    }

    public static boolean isPossible(int min,int max){
        if(heights[startRow][startCol]<min||heights[startRow][startCol]>max){
            return false;
        }
        int mapSize = heights.length;
        boolean[][] visited = new boolean[mapSize][mapSize];
        Deque<List<Integer>> que = new ArrayDeque<>();

        que.offer(Arrays.asList(startRow,startCol));
        visited[startRow][startCol] = true;

        while(!que.isEmpty()){
            List<Integer> beforeLoc = que.poll();
            for(int i=0;i<8;i++){
                int curRow = beforeLoc.get(0)+dRow[i];
                int curCol = beforeLoc.get(1)+dCol[i];
                if(curRow>=mapSize||curRow<0||curCol>=mapSize||curCol<0){
                    continue;
                }
                if(visited[curRow][curCol]){
                    continue;
                }
                if(heights[curRow][curCol]<min||heights[curRow][curCol]>max){
                    continue;
                }
                que.offer(Arrays.asList(curRow,curCol));
                visited[curRow][curCol] = true;
            }
        }
        if(doesVisitAllDestination(visited)){
            return true;
        }
        return false;
    }

    public static boolean doesVisitAllDestination(boolean[][] visited){
        for(List<Integer> destination : destinations){
            if(!visited[destination.get(0)][destination.get(1)]){
                return false;
            }
        }
        return true;
    }
}
