import java.util.*;
class Solution {
    public int rowSize;
    public int colSize;
    public int startRow=0;
    public int startCol=0;
    public String[][] realPark;
    public int[] solution(String[] park, String[] routes) {
        int[] answer = {0,0};
        rowSize = park.length;
        colSize = park[0].length();
        
        realPark = new String[rowSize][colSize];
        
        for(int i=0;i<rowSize;i++){
            for(int j=0;j<colSize;j++){
                realPark[i][j] = Character.toString(park[i].charAt(j));
                if(realPark[i][j].equals("S")){
                    startRow=i;
                    startCol=j;
                }
            }
        }
        
        Map<String,List<Integer>> direct = new HashMap<>();
        direct.put("E",List.of(0,1));
        direct.put("W",List.of(0,-1));
        direct.put("S",List.of(1,0));
        direct.put("N",List.of(-1,0));
        
        int curRow = startRow;
        int curCol = startCol;
        
        
        for(String route:routes){
            String[] routeParsed = route.split(" ");
            List<Integer> curDirection = direct.get(routeParsed[0]);
            
            int distance = Integer.parseInt(routeParsed[1]);
            int candidateRow = curRow+curDirection.get(0)*distance;
            int candidateCol = curCol+curDirection.get(1)*distance;
            if(candidateRow<0||candidateRow>=rowSize||candidateCol<0||candidateCol>=colSize){
                continue;
            }
            
            boolean doesGo=true;
            candidateRow = curRow;
            candidateCol = curCol;
            for(int i=0;i<distance;i++){
                candidateRow+=curDirection.get(0);
                candidateCol+=curDirection.get(1);
                if(realPark[candidateRow][candidateCol].equals("X")){
                    doesGo = false;
                    break;
                }
            }
            
            if(doesGo){
                curRow = candidateRow;
                curCol = candidateCol;
            }
        }
        
        answer[0] = curRow;
        answer[1] = curCol;
        return answer;
    }
}