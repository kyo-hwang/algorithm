import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        List<List<Integer>> point = new ArrayList<>();
        for(int i=0;i<n;i++){
            point.add(Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
        }
        point.sort(Comparator.comparingInt(e->e.get(0)));

        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int curPossible = input[1];
        int dest = input[0];

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(e->-e));

        int nextPoint = 0;
        int result=0;

        while(curPossible<dest){
            while(nextPoint<n&&point.get(nextPoint).get(0)<=curPossible){
                pq.offer(point.get(nextPoint).get(1));
                nextPoint++;
            }
            if(!pq.isEmpty()){
                curPossible+=pq.poll();
                result++;
            }else{
                break;
            }
        }

        if(curPossible<dest){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }

    }
}
