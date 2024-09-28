import java.util.*;
import java.io.*;

class Solution {
    public int[][] matrix;
    public int rows;
    public int cols;
    
    public int[] dRow = {0,1,0,-1};
    public int[] dCol = {1,0,-1,0};
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        this.rows = rows;
        this.cols = columns;
        matrix = new int[rows+1][cols+1];
        
        for(int i=1;i<=rows;i++){
            for(int j=1;j<=cols;j++){
                matrix[i][j] = (i-1)*cols+j;
            }
        }
        // for(int i=0;i<matrix.length;i++){
        //     System.out.println(Arrays.toString(matrix[i]));
        // }
        for(int i=0;i<queries.length;i++){
            answer[i] = execute(queries[i]);
            // for(int j=0;j<matrix.length;j++){
            //     System.out.println(Arrays.toString(matrix[j]));
            // }
        }
        
        return answer;
    }
    public int execute(int[] query){
        int x1 = query[0];
        int y1 = query[1];
        int x2 = query[2];
        int y2 = query[3];
        
        int[] sRow = {x1,x1,x2,x2};
        int[] sCol = {y1,y2,y2,y1};
        
        int[] startVal = {matrix[x1][y1],matrix[x1][y2],matrix[x2][y2],matrix[x2][y1]};
        
        int min = Integer.MAX_VALUE;
        
        for(int i=0;i<4;i++){
            int cRow = sRow[i]+dRow[i]*2;
            int cCol = sCol[i]+dCol[i]*2;
            int before = matrix[cRow-dRow[i]][cCol-dCol[i]];
            while(cRow>=x1&&cRow<=x2&&cCol>=y1&&cCol<=y2){
                int tmp = matrix[cRow][cCol];
                min = Math.min(before,min);
                matrix[cRow][cCol] = before;
                before = tmp;
                cRow+=dRow[i];
                cCol+=dCol[i];
            }
            min = Math.min(startVal[i],min);
            matrix[sRow[i]+dRow[i]][sCol[i]+dCol[i]] = startVal[i];
        }
        return min;
    }
}