import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Main{
    static int n;
    static int[][] array;

    static int[] dRow = {0,1,0,-1};
    static int[] dCol = {1,0,-1,0};
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        array = new int[n][n];
        for(int i=0;i<n;i++){
            array[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int min = 200;
        int max = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                min = Math.min(min,array[i][j]);
                max = Math.max(max,array[i][j]);
            }
        }

        int tmpMin = 0;
        int tmpMax = max-min;

        while(tmpMin<=tmpMax){
            boolean rangePossible = false;
            int mid = (tmpMin+tmpMax)/2;
            for(int to=min+mid;to<=max;to++){
                if(canMove(to-mid,to)){
                    tmpMax = mid-1;
                    rangePossible = true;
                    break;
                }
            }
            if(!rangePossible){
                tmpMin = mid+1;
            }
        }
        System.out.println(tmpMin);
    }



    public static boolean canMove(int from,int to){
        boolean[][] canVisit = new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(array[i][j]>=from&&array[i][j]<=to){
                    canVisit[i][j] = true;
                }
            }
        }
        Deque<List<Integer>> que = new  ArrayDeque<>();
        if(!canVisit[0][0]){
            return false;
        }
        que.offer(Arrays.asList(0,0));
        canVisit[0][0] =false;
        while (!que.isEmpty()){
            List<Integer> loc = que.poll();
            if(loc.get(0)==n-1&&loc.get(1)==n-1){
                return true;
            }
            for(int i=0;i<4;i++){
                int nextRow = loc.get(0)+dRow[i];
                int nextCol = loc.get(1)+dCol[i];

                if(nextCol<0||nextCol>=n||nextRow<0||nextRow>=n){
                    continue;
                }
                if(!canVisit[nextCol][nextRow]){
                    continue;
                }

                canVisit[nextCol][nextRow] =false;
                que.offer(Arrays.asList(nextRow,nextCol));
            }
        }
        return false;
    }
}
