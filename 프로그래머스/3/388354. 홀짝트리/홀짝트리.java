import java.util.*;
class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = {0,0};
        Map<Integer,Set<Integer>> graph = new HashMap<>();
        boolean[] visited = new boolean[1000001];
        for(int node:nodes){
            graph.put(node,new HashSet<>());
        }
        
        for(int[] edge:edges){
            addChild(graph,edge[0],edge[1]);
            addChild(graph,edge[1],edge[0]);
        }
        
        int result = 0;
        for(int node:graph.keySet()){
            int[] count = new int[2];
            if(visited[node]){
                continue;
            }
            Deque<Integer> q = new ArrayDeque<>();
            q.offer(node);
            visited[node] = true;
            //1는 역노드의 개수, 0은 일반노드의 개수
            if(graph.get(node).isEmpty()){
                if(node%2==1){
                    answer[1]++;
                }else{
                    answer[0]++;
                }
                continue;
            }
            count[isReverse(node,graph.get(node).size()-1)]++;
            while(!q.isEmpty()){
                int bnode = q.poll();
                for(int curNode:graph.get(bnode)){
                    if(visited[curNode]){
                        continue;
                    }
                    visited[curNode] = true;
                    q.offer(curNode);
                    count[isReverse(curNode,graph.get(curNode).size()-1)]++;
                }
            }
            if(count[0]==1){
                answer[1]++;
            }
            if(count[1]==1){
                answer[0]++;
            }
        }
        return answer;
    }
    
    public int isReverse(int node,int childCount){
        return (int)Math.abs(node%2-childCount%2);
    }
    
    public void addChild(Map<Integer,Set<Integer>> graph,int e1,int e2){
        graph.get(e1).add(e2);
    }
}