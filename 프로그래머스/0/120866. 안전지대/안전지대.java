class Solution {
    public int[] dCol = {1,1,0,-1,-1,-1,0,1};
    public int[] dRow = {0,1,1,1,  0, -1,-1,-1};
    
    public int solution(int[][] board) {
        int answer = 0;
        int rowSize = board.length;
        int colSize = board[0].length;
        
        boolean[][] isUnSafe = new boolean[rowSize][colSize];
        
        for(int i=0;i<rowSize;i++){
            for(int j=0;j<colSize;j++){
                if(board[i][j]==1){
                    isUnSafe[i][j] = true;
                    for(int k=0;k<8;k++){
                        int rowToCheck = i+dRow[k];
                        int colToCheck = j+dCol[k];
                        if(rowToCheck>=0&&rowToCheck<rowSize&&colToCheck>=0&&colToCheck<colSize){
                            isUnSafe[rowToCheck][colToCheck] = true;
                        }
                    }
                }
            }
        }
        
        for(int i=0;i<rowSize;i++){
            for(int j=0;j<colSize;j++){
                if(!isUnSafe[i][j]){
                    answer++;
                }
            }
        }
        
        return answer;
    }
}