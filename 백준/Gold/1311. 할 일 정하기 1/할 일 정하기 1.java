import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] costTable;
    public static int[] dp;
    private static boolean[] visited;
    public static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(bf.readLine());
        costTable = new int[n][n];

        for(int i=0;i<n;i++){
            costTable[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp = new int[1<<n];
        visited = new boolean[1<<n];
//        for(int i=0;i<n;i++){
//            dp.add(new HashMap<>());
//        }
//        printDp();

        System.out.println(take(0,new BitSet()));

//        printDp();

    }
    public static void printDp(){
        System.out.println(dp);

    }

    public static int take(int depth,BitSet workState){
//        System.out.println(workState);
        if(depth==n-1){
            int lastRemain = workState.nextClearBit(0);
            return costTable[depth][lastRemain];
        }
        int index = convert(workState);

        if(visited[index]){
            return dp[index];
        }

        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(!workState.get(i)){
                BitSet assigned = (BitSet)workState.clone();
                assigned.set(i);

                min = Math.min(take(depth+1,assigned)+costTable[depth][i],min);
            }
        }
        dp[index] = min;
        visited[index] = true;
//        System.out.println(dp);
        return min;
    }
    public static int convert(BitSet bitSet){
        int value=0;
        for(int i=0;i<bitSet.length();i++){
            value += bitSet.get(i) ? (1<<i) : 0;
        }
        return value;
    }
}
