import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static class Route {
        int destination;
        int length;
        public Route(int destination, int length){
            this.destination = destination;
            this.length = length;
        }
    }
    public static int interCount;
    public static int routeCount;
    public static List<List<Route>> routes = new ArrayList<>();

    public static int[][] shortestPaths;
    public static String[][] firstInter;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        interCount = input[0];
        routeCount = input[1];
        for(int i=0;i<interCount;i++){
            routes.add(new ArrayList<>());
        }

        for(int i = 0; i< routeCount; i++){
            input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int i1 = input[0]-1;
            int i2 = input[1]-1;
            int length = input[2];
            routes.get(i1).add(new Route(i2,length));
            routes.get(i2).add(new Route(i1,length));
        }

        shortestPaths = new int[interCount][interCount];
        firstInter = new String[interCount][interCount];
        for(int i=0;i<interCount;i++){
            Arrays.fill(shortestPaths[i],Integer.MAX_VALUE);
        }
        for(int startInter=0;startInter<interCount;startInter++){
            //list 0번 inter 번호, 1번 첫번째 거친 inter, 2번 거리.
            PriorityQueue<List<Integer>> pq = new PriorityQueue<>(Comparator.comparing(e->e.get(2)));
            shortestPaths[startInter][startInter] = 0;
            firstInter[startInter][startInter] = "-";
            for(Route route:routes.get(startInter)){
                pq.offer(List.of(route.destination,route.destination,route.length));
            }
            while(!pq.isEmpty()){
                List<Integer> curMin = pq.poll();
                if(curMin.get(2)>= shortestPaths[startInter][curMin.get(0)]){
                    continue;
                }
                shortestPaths[startInter][curMin.get(0)] = curMin.get(2);
                firstInter[startInter][curMin.get(0)] = Integer.toString(curMin.get(1)+1);
                for(Route route:routes.get(curMin.get(0))){
                    int nextInter = route.destination;
                    int shortestPathCandidate = route.length+curMin.get(2);
                    if(shortestPathCandidate>=shortestPaths[startInter][nextInter]){
                        continue;
                    }
                    pq.add(List.of(nextInter,curMin.get(1),shortestPathCandidate));
                }
            }

        }
        
        for(int i=0;i<interCount;i++){
            for(int j=0;j<interCount;j++){
                System.out.print(firstInter[i][j]+" ");
            }
            System.out.println();
        }
    }
}
