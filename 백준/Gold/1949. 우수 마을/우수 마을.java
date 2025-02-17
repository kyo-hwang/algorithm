import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int n;
    public static int[] populations;
    public static List<List<Integer>> relations = new ArrayList<>();
    public static List<List<Integer>> tree = new ArrayList<>();
    public static int[][] dp;
    public static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        for(int i=0;i<n;i++){
            relations.add(new ArrayList<>());
            tree.add(new ArrayList<>());
        }
        populations = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i=0;i<n-1;i++){
            int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            relations.get(input[0]-1).add(input[1]-1);
            relations.get(input[1]-1).add(input[0]-1);
        }

        dp = new int[2][n];
        Arrays.fill(dp[0],-1);
        Arrays.fill(dp[1],-1);
        visited = new boolean[n];
        explore(0);
        int c1 = calMaxPopulation(0,0);
        int c2 = calMaxPopulation(0,1);
        System.out.println(Math.max(c1,c2));

    }

    public static void explore(int cur){
        visited[cur] = true;
        for(int childId:relations.get(cur)){
            if(visited[childId]){
                continue;
            }
            tree.get(cur).add(childId);
            explore(childId);
        }
    }

    public static int calMaxPopulation(int villageId,int isContained){
        if(dp[isContained][villageId]!=-1){
            return dp[isContained][villageId];
        }
        if(isContained==1){
            int sum = populations[villageId];
            for(int childId:tree.get(villageId)){
                sum+=calMaxPopulation(childId,0);
            }
            dp[isContained][villageId] = sum;
            return sum;
        }else{
            int sum = 0;
            for(int childId:tree.get(villageId)){
                int notContainedValue = calMaxPopulation(childId,0);
                int containedValue = calMaxPopulation(childId,1);
                sum+=Math.max(notContainedValue,containedValue);
            }
            dp[isContained][villageId] = sum;
            return sum;
        }
    }
}
