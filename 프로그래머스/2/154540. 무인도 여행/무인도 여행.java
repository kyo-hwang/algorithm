import java.util.*;
class Solution {
    public int[][] realMap;
    public int rs;
    public int cs;
    public int[] dRow = {0,1,0,-1};
    public int[] dCol = {1,0,-1,0};
    public boolean[][] visited;
    
    public int[] solution(String[] maps) {
        int[] answer = {};
        
        List<Integer> result = new ArrayList<>();
        init(maps);
        
        for(int i=0;i<rs;i++){
            for(int j=0;j<cs;j++){
                if(realMap[i][j]==-1){
                    continue;
                }
                if(visited[i][j]){
                    continue;
                }
                result.add(bfs(i,j));
            }
        }
        if(result.isEmpty()){
            result.add(-1);
        }
        
        answer = result.stream().mapToInt(e->e).toArray();
        Arrays.sort(answer);
        
        return answer;
    }
    
    public int bfs(int sr,int sc){
        int result =0;
        Deque<List<Integer>> q = new ArrayDeque<>();
        q.offer(List.of(sr,sc));
        visited[sr][sc] = true;
        result+=realMap[sr][sc];
        while(!q.isEmpty()){
            List<Integer> bi = q.poll();
            int br = bi.get(0);
            int bc = bi.get(1);
            for(int i=0;i<4;i++){
                int cr = br+dRow[i];
                int cc = bc+dCol[i];
                if(cr<0||cr>=rs||cc<0||cc>=cs){
                    continue;
                }
                if(visited[cr][cc]){
                    continue;
                }
                if(realMap[cr][cc]==-1){
                    continue;
                }
                result+=realMap[cr][cc];
                q.offer(List.of(cr,cc));
                visited[cr][cc] = true;
            }
        }
        return result;
    }
    
    
    
    public void init(String[] maps){
        rs = maps.length;
        cs = maps[0].length();
        realMap = new int[rs][cs];
        for(int i=0;i<maps.length;i++){
            for(int j=0;j<maps[i].length();j++){
                if(maps[i].charAt(j)=='X'){
                    realMap[i][j] = -1;
                }else{
                    realMap[i][j] = Integer.parseInt(Character.toString(maps[i].charAt(j)));
                }
            }
        }
        visited = new boolean[rs][cs];
    }
}