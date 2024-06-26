import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static int uN;
    private static int rN;

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> commands = Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        uN = commands.get(0);
        rN = commands.get(1);
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<uN;i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0;i<rN;i++){
            commands = Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            int v = commands.get(0);
            int u = commands.get(1);

            graph.get(v-1).add(u-1);
            graph.get(u-1).add(v-1);
        }
        int tmpMin = Integer.MAX_VALUE;
        int minV = -1;
        for(int i=0;i<uN;i++){
            int KN = findKN(graph,i);
            if(KN<tmpMin){
                tmpMin = KN;
                minV = i;
            }
        }
        System.out.println(minV+1);
    }
    public static int findKN(List<List<Integer>> graph, int v){
        boolean[] visited = new boolean[uN];
        int KN = 0;
        Deque<List<Integer>> que = new ArrayDeque<>();
        visited[v] = true;
        que.offer(new ArrayList<>(Arrays.asList(v,0)));
        while(!que.isEmpty()){
            List<Integer> e = que.poll();
            v = e.get(0);
            int d = e.get(1);
            for(int u:graph.get(v)){
                if(visited[u]){
                    continue;
                }
                visited[u] = true;
                KN+=d+1;
                que.offer(new ArrayList<>(Arrays.asList(u,d+1)));
            }
        }
        return KN;
    }

}
