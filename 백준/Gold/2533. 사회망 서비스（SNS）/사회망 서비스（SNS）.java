import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static boolean[] visited;
    public static List<List<Integer>> graph;
    public static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        graph = new ArrayList<>();
        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<n-1;i++){
            int[] c = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph.get(c[0]-1).add(c[1]-1);
            graph.get(c[1]-1).add(c[0]-1);
        }

        visited = new boolean[n];
        dp = new int[n];
        areChildEarlyAdapter(0);
        System.out.println(dp[0]);
    }

    public static boolean areChildEarlyAdapter(int node){
        visited[node] = true;
        boolean result = true;
        int sum = 0;

        for(int nextNode : graph.get(node)){
            if(!visited[nextNode]){
                result = areChildEarlyAdapter(nextNode)&&result;
                sum += dp[nextNode];
            }
        }
        if(!result){
            dp[node] = sum+1;
            return true;
        }
        else{
            dp[node] = sum;
            return false;
        }
    }
}
