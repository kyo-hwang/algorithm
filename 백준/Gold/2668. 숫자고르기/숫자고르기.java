import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int first;
    static int max;
    static boolean[] findPossible;
//    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int[] graph = new int[n];

        for(int i=0;i<n;i++){
            graph[i] = Integer.parseInt(bf.readLine())-1;
        }

        findPossible = new boolean[n];

        for(int i=0;i<n;i++){
            boolean[] visited = new boolean[n];
            for(int j=0;j<n;j++){
                visited[j] = findPossible[j];
            }
            first = i;
            dfs(i,graph,visited,0);
        }

        System.out.println(max);
//        System.out.println(Arrays.toString(findPossible));
        for(int i=0;i<n;i++){
            if(findPossible[i]){
                System.out.println(i+1);
            }
        }
    }

    public static void dfs(int v, int[] graph, boolean[] visited,int depth){
        if(visited[v]){
            if(v == first){
                findPossible = visited;
                max+=depth;
            }
            return;
        }
        visited[v] = true;
        int next = graph[v];
        dfs(next,graph,visited,depth+1);
    }
}
