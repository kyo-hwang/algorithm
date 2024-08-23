import java.util.*;
import java.io.*;

class Solution {
    class Location{
        public Location(int row,int col){
            this.row = row;
            this.col = col;
        }
        int row;
        int col;
        
        public int getRow(){
            return row;
        }
        
        public int getCol(){
            return col;
        }
    }
    
    public static int[] dCol = {1,0,-1,0};
    public static int[] dRow = {0,1,0,-1};
    
    static int row;
    static int col;
    int[] amountPerLoc;
    boolean[][] visited;
    
    public int solution(int[][] land) {
        row = land.length;
        col = land[0].length;
        
        amountPerLoc = new int[col];
        visited = new boolean[row][col];
        
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                bfs(i,j,land);
            }
        }
        int answer = 0;
        for(int i=0;i<col;i++){
            answer = Math.max(answer,amountPerLoc[i]);
        }
        return answer;
    }
    
    public void bfs(int rowLoc,int colLoc,int[][] land){
        if(visited[rowLoc][colLoc]||land[rowLoc][colLoc]==0){
            return;
        }
        Set<Integer> history = new HashSet<>();
        Deque<Location> deque = new ArrayDeque<>();
        deque.offer(new Location(rowLoc,colLoc));
        visited[rowLoc][colLoc] = true;
        history.add(colLoc);
        int amount = 1;
        
        while(!deque.isEmpty()){
            Location loc = deque.poll();
            for(int i=0;i<4;i++){
                int nextRow = loc.getRow()+dRow[i];
                int nextCol = loc.getCol()+dCol[i];
                if(nextRow<0||nextRow>=row||nextCol<0||nextCol>=col){
                    continue;
                }
                if((!visited[nextRow][nextCol])&&land[nextRow][nextCol]==1){
                    visited[nextRow][nextCol] = true;
                    deque.offer(new Location(nextRow,nextCol));
                    history.add(nextCol);
                    amount++;
                }
            }
        }
        
        for(int possibleCol:history){
            amountPerLoc[possibleCol] +=amount;
        }
    }
    
}