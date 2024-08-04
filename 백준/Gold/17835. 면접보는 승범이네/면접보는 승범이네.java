import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static class Edge{
        public Edge(int node,int distance){
            this.node = node;
            this.distance = distance;
        }
        int node;
        int distance;

        public int getNode(){
            return node;
        }
        public int getDistance(){
            return distance;
        }
    }

    public static class UnsettledNode{
        public UnsettledNode(int node,long weight){
            this.node = node;
            this.weight = weight;
        }
        int node;
        long weight;

        public int getNode(){
            return node;
        }

        public long getWeight(){
            return weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] commands = Arrays.stream(bf.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int nodeNum = commands[0];
        int edgeNum = commands[1];
        int interViewNodeNum = commands[2];

        List<List<Edge>> graph = Stream.generate(()->1)
                .limit(nodeNum)
                .map((e)->new ArrayList<Edge>())
                .collect(Collectors.toList());

        for(int i=0;i<edgeNum;i++){
            int[] rel = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph.get(rel[1]-1).add(new Edge(rel[0]-1,rel[2]));
        }

        PriorityQueue<UnsettledNode> pq = new PriorityQueue<>((e1,e2)->{
            if((e1.getWeight()-e2.getWeight())>0){
                return 1;
            } else if (e1.getWeight()-e2.getWeight()==0) {
                return 0;
            }
            return -1;
        });

        int[] interviewNodes = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Long> weightTable = new ArrayList<>(Collections.nCopies(nodeNum,Long.MAX_VALUE));
        for(int interviewNode:interviewNodes){
//            weightTable.set(interviewNode,0L);
            pq.offer(new UnsettledNode(interviewNode-1,0));
        }

        while (!pq.isEmpty()){
            UnsettledNode node = pq.poll();
            if(node.getWeight()>weightTable.get(node.getNode())){
                continue;
            }
            weightTable.set(node.getNode(),node.getWeight());
            for(Edge edge:graph.get(node.getNode())){
                if(node.getWeight()+edge.getDistance()<weightTable.get(edge.getNode())){
                    pq.offer(new UnsettledNode(edge.getNode(),node.getWeight()+edge.getDistance()));
                    weightTable.set(edge.getNode(),node.getWeight()+edge.getDistance());
                }
            }
        }
        long max = -1;
        long maxNode = -1;
        for(int i=0;i<nodeNum;i++){
            if(weightTable.get(i)>max){
                max = weightTable.get(i);
                maxNode = i;
            }
        }
//        System.out.println(weightTable);
        System.out.println(maxNode+1);
        System.out.println(max);
    }
}
