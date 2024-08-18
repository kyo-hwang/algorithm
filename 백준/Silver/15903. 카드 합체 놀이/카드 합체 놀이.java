import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int cardN = input[0];
        int mixN = input[1];

        long[] cards = Arrays.stream(bf.readLine().split(" ")).mapToLong(Integer::parseInt).toArray();

        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(long card : cards){
            pq.add(card);
        }
        long sum = Arrays.stream(cards).sum();

        for(int i=0;i<mixN;i++){
            long small = pq.poll();
            long small2 = pq.poll();
            long cardSum = small2+small;
            sum+=cardSum;
            pq.offer(cardSum);
            pq.offer(cardSum);

        }

        System.out.println(sum);
    }
}
