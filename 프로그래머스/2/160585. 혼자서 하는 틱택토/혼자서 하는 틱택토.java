import java.util.*;
class Solution {
    public int solution(String[] board) {
        int answer = -1;
        String[][] realBoard = new String[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                realBoard[i][j] =Character.toString(board[i].charAt(j)); 
            }
        }
        
        int oCount = 0;
        int xCount = 0;
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(realBoard[i][j].equals("O")){
                    oCount++;
                }
                else if(realBoard[i][j].equals("X")){
                    xCount++;
                }
            }
        }

        boolean oWin = checkWin(realBoard,"O");
        boolean xWin = checkWin(realBoard,"X");
        
        int result = 1;
        
        if(!oWin&&!xWin){
            if(!(oCount-xCount==1||oCount-xCount==0)){
                result = 0;
            }
        }else if(oWin){
            if(xWin){
                result  = 0;
            }else{
                if(!(oCount-xCount==1)){
                    result = 0;
                }
            }
        }else{
            if(oCount-xCount!=0){
                result = 0;
            }
        }
    
        
        answer =result;
        
        return answer;
    }
    
    public boolean checkWin(String[][] board,String player){
        for(int i=0;i<3;i++){
            int count=0;
            for(int j=0;j<3;j++){
                if(board[i][j].equals(player)){
                    count++;
                }
            }
            if(count==3){
                return true;
            }
        }
        
        
        for(int i=0;i<3;i++){
            int count=0;
            for(int j=0;j<3;j++){
                if(board[j][i].equals(player)){
                    count++;
                }
            }
            if(count==3){
                return true;
            }
        }
        
        int count=0;
        for(int i=0;i<3;i++){
            if(board[i][i].equals(player)){
                count++;
            }
            if(count==3){
                return true;
            }
        }
        
        count=0;
        for(int i=0;i<3;i++){
            if(board[i][2-i].equals(player)){
                count++;
            }
            if(count==3){
                return true;
            }
        }
        return false;
    }
}