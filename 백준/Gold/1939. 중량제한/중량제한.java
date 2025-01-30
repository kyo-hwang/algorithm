import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main{
    public static class Bridge{
        private int destination;
        private int length;

        public Bridge(int destination,int length){
            this.destination = destination;
            this.length = length;
        }
    }

    public static class Distance implements Comparable<Distance>{
        private int island;
        private int amount;

        public Distance(int island,int amount){
            this.island = island;
            this.amount = amount;
        }

        @Override
        public int compareTo(Distance o) {
            return o.amount-this.amount;
        }
    }


    public static int n;
    public static int m;

    public static int start;
    public static int destination;
    public static int[] distances;
    public static boolean[] visited;
    public static List<List<Bridge>> islandInfo = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];

        for(int i=0;i<n;i++){
            islandInfo.add(new ArrayList<>());
        }

        for(int i=0;i<m;i++){
            int[] bridge = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            islandInfo.get(bridge[0]-1).add(new Bridge(bridge[1]-1,bridge[2]));
            islandInfo.get(bridge[1]-1).add(new Bridge(bridge[0]-1,bridge[2]));
        }

        input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        start = input[0]-1;
        destination = input[1]-1;

        distances = new int[n];
        visited = new boolean[n];

        PriorityQueue<Distance> pq = new PriorityQueue<>();
        pq.offer(new Distance(start,Integer.MAX_VALUE));
        while(!pq.isEmpty()){
            Distance d = pq.poll();
            if(d.amount<=distances[d.island]){
                continue;
            }
            distances[d.island] = d.amount;
            for(Bridge bridge:islandInfo.get(d.island)){
                pq.offer(new Distance(bridge.destination, Math.min(bridge.length,d.amount)));
            }
        }

//        System.out.println(Arrays.toString(distances));
        System.out.println(distances[destination]);
    }

}
