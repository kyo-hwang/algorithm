import java.util.*;
import java.util.stream.*;

class Solution {
    public static class History{
        Set<Integer> nodes;
        int edgesNum;
        
        public History(){
            nodes = new HashSet<Integer>();
            edgesNum = 0;
        }
        
        public void addNode(int node){
            nodes.add(node);
        }
        
        public void upEdge(){
            edgesNum++;
        }
        
        public String getType(){
            if(nodes.size()==edgesNum){
                return "donut";
            }
            else if(nodes.size() > edgesNum){
                return "stick";
            }
            else{
                return "eight";
            }
        }
    }
    
    public boolean[] visited = new boolean[1000001];
    public List<List<Integer>> graph;
    
    public int[] solution(int[][] edges) {
        graph = Stream
            .generate(()->1)
            .limit(1000001)
            .map((e)->new ArrayList<Integer>())
            .collect(Collectors.toList());
        
        //새로 생성한 정점은 들어오는 엣지가 0개 일 것이다.
        List<Boolean> isIn = new ArrayList<>(Collections.nCopies(1000001,false));
        
        for(int[] edge:edges){
            graph.get(edge[0]).add(edge[1]);
            isIn.set(edge[1],true);
        }
        
        int newNode = -1;
        //들어오는 정점이 0개이면서 나가는 정점이 2개 이상인 노드가 새로 생성한 정점이다.
        for(int i=0;i<1000001;i++){
            if(!isIn.get(i)){
                if(graph.get(i).size()>=2){
                    newNode = i;
                }
            }
        }
        
        int donutNum=0;
        int eightNum=0;
        int stickNum=0;
        
        for(int startNode: graph.get(newNode)){
            History history = new History();
            bfs(history,startNode);
            
            String type = history.getType();
            
            if(type.equals("donut")){
                donutNum++;
            }else if(type.equals("eight")){
                eightNum++;
            }
            else{
                stickNum++;
            }
        }
        
        int[] answer = {newNode,donutNum,stickNum,eightNum};
        return answer;
    }
    
    public void bfs(History history,int startNode){
        Deque<Integer> que = new ArrayDeque<>();
        que.offer(startNode);
        visited[startNode] = true;
        history.addNode(startNode);
        
        while(!que.isEmpty()){
            int nodeAlreadyVisited = que.poll();
            for(int nodeToVisit:graph.get(nodeAlreadyVisited)){
                if(!visited[nodeToVisit]){
                    visited[nodeToVisit] = true;
                    que.offer(nodeToVisit);
                    history.addNode(nodeToVisit);
                }
                history.upEdge();
            }
        }
    }
}