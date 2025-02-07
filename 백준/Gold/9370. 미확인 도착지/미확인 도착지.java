import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static class Road{
        int end;
        int length;
        public Road(int end,int length){
            this.end = end;
            this.length = length;
        }

        public String toString(){
            return "{ end: "+end+",length : "+length+" }";
        }
    }
    public static int nodeCount;
    public static int roadCount;
    public static int destinationCandidateCount;
    public static int start;
    public static int g;
    public static int h;
    public static List<List<Road>> graph ;
    public static List<Integer> destinationCandidate;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        for(int i=0;i<t;i++){
            int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nodeCount = input[0];
            graph = new ArrayList<>();
            for(int j=0;j<nodeCount;j++){
                graph.add(new ArrayList<>());
            }
            roadCount = input[1];
            destinationCandidateCount = input[2];
            input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            start = input[0]-1;
            g = input[1]-1;
            h = input[2]-1;


            for(int j=0;j<roadCount;j++){
                input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                graph.get(input[0]-1).add(new Road(input[1]-1,input[2]));
                graph.get(input[1]-1).add(new Road(input[0]-1,input[2]));
            }//같은 입력이 들어올 수 있나?


            destinationCandidate = new ArrayList<>();
            for(int j=0;j<destinationCandidateCount;j++){
                destinationCandidate.add(Integer.parseInt(bf.readLine())-1);
            }

            int[] lengthFromStart = dijkstra(start);
            int nextStartNodeId;
            if(lengthFromStart[g]>lengthFromStart[h]){
                nextStartNodeId = g;
            }else{
                nextStartNodeId = h;
            }

            int[] lengthFromNextStart = dijkstra(nextStartNodeId);
            int midNodeLength = lengthFromStart[nextStartNodeId];
            List<Integer> result = new ArrayList<>();
            for(Integer candidate: destinationCandidate){
                if(midNodeLength+lengthFromNextStart[candidate]==lengthFromStart[candidate]){
                    result.add(candidate+1);
                }
            }
            result.sort(Comparator.comparingInt(e->e));
            for(int e:result){
                System.out.print(e+" ");
            }
            System.out.println();
        }
    }

    public static int[] dijkstra(int startNode){
        int[] lengths = new int[nodeCount];
        Arrays.fill(lengths,Integer.MAX_VALUE);
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.get(1)));
        pq.offer(List.of(startNode,0));
        while(!pq.isEmpty()){
            List<Integer> curNode = pq.poll();
            int curLength = curNode.get(1);
            int curNodeId = curNode.get(0);

            if(curLength>=lengths[curNodeId]){
                continue;
            }
            lengths[curNodeId] = curLength;
            for(Road road:graph.get(curNodeId)){
                pq.offer(List.of(road.end,road.length+curLength));
            }
        }
        return lengths;
    }
}
