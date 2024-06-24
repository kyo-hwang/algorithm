import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static boolean[] primeTable;
    public static List<List<Integer>> graph;
    public static void initPrimeTable(){
        boolean[] table = new boolean[10000];
        Arrays.fill(table,true);
        for(int i=2;i<10000;i++){
            for(int j=i*2;j<10000;j+=i){
                table[j] = false;
            }
        }
        primeTable = table;
    }
    public static void initGraph(){
        graph = new ArrayList<>();
        for(int i=0;i<1000;i++){
            graph.add(new ArrayList<>());
        }
        for(int i=1000;i<10000;i++){
            List<Integer> low = new ArrayList<>();
            if(!primeTable[i]){
                graph.add(low);
                continue;
            }
            for(int j=0;j<4;j++){
                StringBuffer tmp = new StringBuffer(Integer.toString(i));
                tmp.setCharAt(j,'0');
                int num = Integer.parseInt(tmp.toString());
                int k=0;
                if(j==0){
                    k=1;
                }
                for(;k<10;k++){
                    int e = num+(int)Math.pow(10,3-j)*k;
                    if(e==num && !primeTable[e]){
                        continue;
                    }
                    low.add(e);
                }
            }
            graph.add(low);
        }
    }

    public static int bfs(int src,int dst){
        if(src==dst){
            return 0;
        }
        boolean[] visited = new boolean[10000];
        Deque<List<Integer>> deque = new ArrayDeque<>();
        deque.offer(new ArrayList<>(Arrays.asList(src,0)));

        while(!deque.isEmpty()){
            List<Integer> e = deque.poll();
            int n = e.get(0);
            int d = e.get(1);
            for(int v:graph.get(n)){
                if(!visited[v]){
                    if(v==dst){
                        return d+1;
                    }
                    visited[v] = true;
                    deque.offer(new ArrayList<>(Arrays.asList(v,d+1)));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        initPrimeTable();
        initGraph();

        int n = Integer.parseInt(bf.readLine());

        List<List<Integer>> input = new ArrayList<>();
        for(int i=0;i<n;i++){
            input.add( Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
        }

        for(int i=0;i<n;i++){
            int src = input.get(i).get(0);
            int dst = input.get(i).get(1);
            int result = bfs(src,dst);
            if(result==-1){
                System.out.println("Impossible");
            }
            else{
                System.out.println(result);
            }
        }
    }
}
