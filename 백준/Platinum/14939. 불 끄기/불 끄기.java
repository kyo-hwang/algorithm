import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.Arrays;

public class Main {
    public static int[] dRow = {0,1,0,-1};
    public static int[] dCol = {1,0,-1,0};
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[][] lightBoard = new String[10][10];
        for(int i=0;i<10;i++){
            lightBoard[i] = bf.readLine().split("");
        }

        int result = findMinSwitch(lightBoard,0,0);
//        System.out.println("r  "+result);

        if(result == Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }

        System.out.println(result);

    }

    public static int findMinSwitch(String[][] lightBoard,int depth,int switchNum){
        if(depth==10){
            int belowNum = calSwitchNum(lightBoard);
            if(belowNum==Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }

            return belowNum+switchNum;
        }

        String[][] boardOn = copyArray(lightBoard);
        turnOn(boardOn,0,depth);
        String[][] boardNothing = copyArray(lightBoard);


        int minOn = findMinSwitch(boardOn,depth+1,switchNum+1);
        int minNoting = findMinSwitch(boardNothing,depth+1,switchNum);

        return Math.min(minOn,minNoting);
    }

    public static int calSwitchNum(String[][] lightBoard){
        int num =0;

        for(int i=1;i<10;i++){
            for(int j=0;j<10;j++){
                if(lightBoard[i-1][j].equals("O")){
                    turnOn(lightBoard,i,j);
                    num++;
                }
            }
        }

        for(int i=0;i<10;i++){
            if(lightBoard[9][i].equals("O")){
                return Integer.MAX_VALUE;
            }
        }
        return num;
    }

    public static void turnOn(String[][] board, int row,int col){
        change(board,row,col);
        for(int i=0;i<4;i++){
            int rowEffected = row+dRow[i];
            int colEffected = col+dCol[i];
            if(rowEffected<0||rowEffected>=10||colEffected<0||colEffected>=10){
                continue;
            }
            change(board,rowEffected,colEffected);
        }
    }

    public static void change(String[][] board, int row,int col){
        if(board[row][col].equals("#")){
            board[row][col] = "O";
            return;
        }
        board[row][col] = "#";
    }

    public static String[][] copyArray(String[][] array ){
        String[][] newArray = new String[10][10];
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }
}
