import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Main{
    public static int g;
    public static int p;
    public static Integer[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        g = Integer.parseInt(bf.readLine());
        p = Integer.parseInt(bf.readLine());

        Deque<Integer> arrive = new ArrayDeque<>();
        for(int i=0;i<p;i++){
            arrive.offer(Integer.parseInt(bf.readLine()));
        }

        parent = new Integer[g+1];
        for(int i=0;i<g+1;i++){
            parent[i] = i;
        }
        int result = 0;
        while(!arrive.isEmpty()){
            if(put(arrive.poll())==-1){
                break;
            }
            result ++;
        }
        System.out.println(result);
    }

    //루트를 반환하는 메서드 도킹 가능한 위치 반환
    public static int find(int node){
        if(node==parent[node]){
            return node;
        }
        return parent[node] = find(parent[node]);
    }

    public static int put(int node){
        int dockingPlace = find(node);
//        System.out.println(dockingPlace);
        if(dockingPlace==0){
            return -1;
        }
//        int nextDockingPlace = find(dockingPlace-1);
//        System.out.println("next "+nextDockingPlace);
        parent[dockingPlace] = dockingPlace-1;
        return dockingPlace-1;
    }
}
