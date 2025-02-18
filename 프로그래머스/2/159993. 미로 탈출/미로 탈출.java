import java.util.*;
class Solution {
    int[] dRow = {0,1,0,-1};
    int[] dCol ={1,0,-1,0};
    
    int rowSize;
    int colSize;
    String[][] realMaps;
    
    
    public int solution(String[] maps) {
        int answer = 0;
        rowSize = maps.length;
        colSize = maps[0].length();
        
        realMaps = new String[rowSize][colSize];
        int startRow=0;
        int startCol=0;
        
        int leverRow=0;
        int leverCol=0;
        
        int exitRow=0;
        int exitCol=0;
        for(int i=0;i<rowSize;i++){
            for(int j=0;j<colSize;j++){
                realMaps[i][j] = Character.toString(maps[i].charAt(j));
                if(realMaps[i][j].equals("L")){
                    leverRow =i;
                    leverCol =j;
                }else if(realMaps[i][j].equals("E")){
                    exitRow = i;
                    exitCol = j;
                }else if(realMaps[i][j].equals("S")){
                    startRow = i;
                    startCol = j;
                }
            }
        }
        
        int leverDistance = calMinDistance(startRow,startCol,leverRow,leverCol);
        int exitDistance = calMinDistance(leverRow,leverCol,exitRow,exitCol);
        
        if(leverDistance==-1||exitDistance==-1){
            return -1;
        }
        
        
        return leverDistance+exitDistance;
    }
    
    
    
    public int calMinDistance(int startRow,int startCol,int destRow,int destCol){
        boolean[][] visited = new boolean[rowSize][colSize];
        Deque<List<Integer>> deque = new ArrayDeque<>();
        deque.offer(List.of(startRow,startCol,0));
        visited[startRow][startCol] = true;
        
        while(!deque.isEmpty()){
            List<Integer> beforeInfo = deque.poll();
            for(int i=0;i<4;i++){
                int curRow = beforeInfo.get(0)+dRow[i];
                int curCol = beforeInfo.get(1)+dCol[i];
                if(curCol<0||curCol>=colSize||curRow<0||curRow>=rowSize){
                    continue;
                }
                if(visited[curRow][curCol]){
                    continue;
                }
                if(realMaps[curRow][curCol].equals("X")){
                    continue;
                }
                if(curRow==destRow&&curCol==destCol){
                    return beforeInfo.get(2)+1;
                }
                visited[curRow][curCol] = true;
                deque.offer(List.of(curRow,curCol,beforeInfo.get(2)+1));
            }
        }
        return -1;
    }
    
    
}