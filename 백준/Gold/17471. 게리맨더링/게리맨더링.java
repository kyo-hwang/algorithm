import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static int n;
    public static int[] populations;
    public static List<List<Integer>> relations = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        populations = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i=0;i<n;i++){
            int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            relations.add(new ArrayList<>());
            for(int j=1;j<=input[0];j++){
                relations.get(i).add(input[j]-1);
            }
        }
        int result =back(new boolean[n],0);
        if(result==Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        System.out.println(result);
    }
    public static int back(boolean[] groupContained,int depth){
        if(depth==groupContained.length){
            int groupContainedNum = 0;
            for(int i=0;i<groupContained.length;i++){
                if(groupContained[i]){
                    groupContainedNum++;
                }
            }
            if(groupContainedNum==0||groupContainedNum==groupContained.length){
                return Integer.MAX_VALUE;
            }
            int populationContained = bfs(groupContained);
            for(int i=0;i<n;i++){
                groupContained[i] = !groupContained[i];
            }
            int populationNotContained = bfs(groupContained);
            if(populationContained==Integer.MAX_VALUE||populationNotContained==Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }
            return Math.abs(populationContained-populationNotContained);
        }
        int result = Integer.MAX_VALUE;
        boolean[] groupContainedThis = Arrays.copyOf(groupContained,groupContained.length);
        groupContainedThis[depth] = true;
        result = Math.min(back(groupContainedThis,depth+1),result);
        boolean[] groupContainedNot =Arrays.copyOf(groupContained,groupContained.length);
        result = Math.min(back(groupContainedNot,depth+1),result);
        return result;
    }

    public static int bfs(boolean[] isContained){
        int first = -1;
        for(int i=0;i<n;i++){
            if(isContained[i]){
                first = i;
                break;
            }
        }
        int totalPopulation = 0;
        boolean[] visited = new boolean[n];
        Deque<Integer> que = new ArrayDeque<>();
        que.offer(first);
        visited[first] = true;
        totalPopulation+=populations[first];

        while(!que.isEmpty()){
            int before = que.poll();
            for(Integer cur :relations.get(before)){
                if(visited[cur]){
                    continue;
                }
                if(!isContained[cur]){
                    continue;
                }
                que.offer(cur);
                visited[cur] = true;
                totalPopulation+=populations[cur];
            }
        }
        for(int i=0;i<n;i++){
            if(isContained[i]){
                if(!visited[i]){
                    return Integer.MAX_VALUE;
                }
            }
        }
        return totalPopulation;
    }
}
