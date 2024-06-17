import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Stream.of(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] carsWeight = Stream.of(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] times = new int[info[0]];

        Deque<Integer> waiting = new ArrayDeque<>();

        int totalWeight = 0;

        waiting.offer(0);
        totalWeight += carsWeight[0];

        int i =1;
        int time = 1;
        times[0] = 1;

        while(!waiting.isEmpty()){
//            System.out.println(totalWeight);
            time++;
            if(times[waiting.getLast()]+info[1]<=time){
                int carRemoved = waiting.pollLast();
//                System.out.println(carRemoved);
                totalWeight -= carsWeight[carRemoved];
            }
            if(i==info[0]){
                continue;
            }
            if(totalWeight+carsWeight[i]<=info[2]){
                waiting.offerFirst(i);
                totalWeight+=carsWeight[i];
                times[i] = time;
                i++;
            }
        }
        System.out.println(time);
    }
}
