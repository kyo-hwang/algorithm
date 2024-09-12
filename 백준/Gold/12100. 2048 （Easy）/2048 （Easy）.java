import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static int n;
    public static int[][] board;

    public static int max = -1;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(bf.readLine());

        board = new int[n+2][n+2];

        for(int i=0;i<n+2;i++){
            if(i==0||i==n+1){
                for(int j=0;j<n+2;j++){
                    board[i][j] = Integer.MAX_VALUE;
                }
                continue;
            }
            board[i][0] = Integer.MAX_VALUE;
            board[i][n+1] = Integer.MAX_VALUE;
            int[] row = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(row,0,board[i],1,n);
        }

        back(board,0);

        System.out.println(max);

    }

    public static void back(int[][] board,int depth){
        if(depth==5){
            for(int i=1;i<n+1;i++){
                for(int j=1;j<n+1;j++){
                    max = Math.max(board[i][j],max);
                }
            }
            return;
        }

        int[][] boardLeftMoved = moveLeft(copyBoard(board));
        back(boardLeftMoved,depth+1);
        int[][] boardRightMoved = moveRight(copyBoard(board));
        back(boardRightMoved,depth+1);
        int[][] boardUpMoved = moveUp(copyBoard(board));
        back(boardUpMoved,depth+1);
        int[][] boardDownMoved = moveDown(copyBoard(board));
        back(boardDownMoved,depth+1);
    }

    public static int[][] moveUp(int[][] board){
        int possibleLoc;
        boolean upperMerged = false;
        for(int i=1;i<n+1;i++){
            possibleLoc = 1;
            for(int j=1;j<n+1;j++){
                if(board[j][i]==0){
                    continue;
                }
                //숫자 합치기
                if(!upperMerged&&board[possibleLoc-1][i]==board[j][i]){
                    board[possibleLoc-1][i] = board[j][i]*2;
                    board[j][i] = 0;
                    upperMerged = true;
                    continue;
                }
                //합치지도 못하는데 본인 자리
                if(possibleLoc==j){
                    possibleLoc++;
                    upperMerged = false;
                    continue;
                }
                //일반적인 올리기
                board[possibleLoc][i] = board[j][i];
                board[j][i] = 0;
                upperMerged = false;
                possibleLoc++;
            }
        }
        return board;
    }

    public static int[][] moveDown(int[][] board){
        int possibleLoc;
        boolean downMerged = false;
        for(int i=1;i<n+1;i++){
            possibleLoc = n;
            for(int j=n;j>=1;j--){
                if(board[j][i]==0){
                    continue;
                }
                if(!downMerged&&board[possibleLoc+1][i]==board[j][i]){
                    board[possibleLoc+1][i] = board[j][i]*2;
                    board[j][i] = 0;
                    downMerged = true;
                    continue;
                }
                if(possibleLoc==j){
                    possibleLoc--;
                    downMerged = false;
                    continue;
                }

                board[possibleLoc][i] = board[j][i];
                board[j][i] = 0;
                downMerged = false;
                possibleLoc--;
            }
        }
        return board;
    }

    public static int[][] moveLeft(int[][] board){
        int possibleLoc;
        boolean leftMerged = false;
        for(int i=1;i<n+1;i++){
            possibleLoc = 1;
            for(int j=1;j<n+1;j++){
                if(board[i][j]==0){
                    continue;
                }
                if(!leftMerged&&board[i][possibleLoc-1]==board[i][j]){
                    board[i][possibleLoc-1] = board[i][j]*2;
                    board[i][j] = 0;
                    leftMerged = true;
                    continue;
                }
                if(possibleLoc==j){
                    possibleLoc++;
                    leftMerged = false;
                    continue;
                }

                board[i][possibleLoc] = board[i][j];
                possibleLoc ++;
                leftMerged = false;
                board[i][j] = 0;
            }
        }
        return board;
    }

    public static int[][] moveRight(int[][] board){
        int possibleLoc;
        boolean rightMerged = false;
        for(int i=1;i<n+1;i++){
            possibleLoc = n;
            for(int j=n;j>=1;j--){
                if(board[i][j]==0){
                    continue;
                }
                if(!rightMerged&&board[i][possibleLoc+1]==board[i][j]){
                    board[i][possibleLoc+1] = board[i][j]*2;
                    board[i][j] = 0;
                    rightMerged = true;
                    continue;
                }
                if(possibleLoc==j){
                    possibleLoc--;
                    rightMerged = false;
                    continue;
                }


                board[i][possibleLoc] = board[i][j];
                possibleLoc --;
                rightMerged = false;
                board[i][j] = 0;
            }
        }
        return board;
    }

    public static int[][] copyBoard(int[][] board){
        int[][] newBoard = new int[n+2][n+2];

        for(int i=0;i<n+2;i++){
            System.arraycopy(board[i], 0, newBoard[i], 0, n + 2);
        }

        return newBoard;
    }
}
