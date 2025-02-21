import java.util.*;
class Solution {
    int[] dRow = {0,1,0,-1};
    int[] dCol = {1,0,-1,0};
    public int solution(String[] storage, String[] requests) {
        int rs = storage.length+2;
        int cs = storage[0].length()+2;
        String[][] containers = new String[rs][cs];
        for(int i=0;i<rs;i++){
            if(i==0||i==rs-1){
                Arrays.fill(containers[i],"-");
            }
            else{
                for(int j=0;j<cs;j++){
                    if(j==0||j==cs-1){
                        containers[i][j]="-";
                    }else{
                        containers[i][j] = Character.toString(storage[i-1].charAt(j-1));
                    }
                }
            }
        }
        
        for(String request:requests){
            if(request.length()==2){
                allRemove(containers,Character.toString(request.charAt(0)));
            }else if(request.length()==1){
                bfs(containers,request);
            }
        }
        
        int answer = 0;
        
        for(int i=0;i<rs;i++){
            for(int j=0;j<cs;j++){
                if(!containers[i][j].equals("-")){
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    public void allRemove(String[][] containers,String containerFinding){
        int rs = containers.length;
        int cs = containers[0].length;
        for(int i=0;i<rs;i++){
            for(int j=0;j<cs;j++){
                if(containers[i][j].equals(containerFinding)){
                    containers[i][j] = "-";
                }
            }
        }
    }
    
    public void bfs(String[][] containers,String containerFinding){
        int rs = containers.length;
        int cs = containers[0].length;
        boolean[][] visited = new boolean[rs][cs]; 
        Deque<List<Integer>> q = new ArrayDeque<>();
        q.offer(List.of(0,0));
        visited[0][0] = true;
        while(!q.isEmpty()){
            List<Integer> beforeInfo = q.poll();
            for(int i=0;i<4;i++){
                int nextRow = beforeInfo.get(0)+dRow[i];
                int nextCol = beforeInfo.get(1)+dCol[i];
                if(nextRow<0||nextRow>=rs||nextCol<0||nextCol>=cs){
                    continue;
                }
                if(visited[nextRow][nextCol]){
                    continue;
                }
                if(containers[nextRow][nextCol].equals("-")){
                    q.offer(List.of(nextRow,nextCol));
                    visited[nextRow][nextCol]=true;
                    continue;
                }
                if(containers[nextRow][nextCol].equals(containerFinding)){
                    visited[nextRow][nextCol] = true;
                    containers[nextRow][nextCol] = "-";
                }
            }
        }
    }
    
}