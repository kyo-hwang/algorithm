import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static class Point{
        public Point(int leftSum,int rightSum){
            this.leftSum = leftSum;
            this.rightSum = rightSum;
        }
        int leftSum;
        int rightSum;

        public static Point createPointEmpty(){
            return new Point(0,0);
        }
        public static Point createPointAllOne(){
            return new Point(1,1);
        }
        public void addLeftPoint(Point leftPoint){
            this.leftSum += leftPoint.leftSum;
        }

        public void addRightPoint(Point rightPoint){
            this.rightSum += rightPoint.leftSum;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int row = input[0];
        int col = input[1];

        int[][] leftUpDp = new int[row][col];
        int[][] rightUpDp = new int[row][col];
        int[][] leftDownDp = new int[row][col];
        int[][] rightDownDp = new int[row][col];

        int[][] mine = new int[row][col];

        for(int i=0;i<row;i++){
            mine[i] = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int oneNum = 0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(mine[i][j]==1){
                    oneNum++;
                }
            }
        }
        if(oneNum==0){
            System.out.println(0);
            return;
        }

        for(int i=0;i<col;i++){
            if(mine[0][i]==1){
                leftUpDp[0][i] = 1;
                rightUpDp[0][i] = 1;
            }
            if(mine[row-1][i]==1){
                leftDownDp[row-1][i] = 1;
                rightDownDp[row-1][i] = 1;
            }
        }

        for(int i=1;i<row;i++){
            for(int j=0;j<col;j++){
                if (mine[i][j] == 0) {
                    continue;
                }
                if(j!=0){
                    leftUpDp[i][j] = leftUpDp[i-1][j-1]+1;
                }
                if(j!=col-1){
                    rightUpDp[i][j] = rightUpDp[i-1][j+1]+1;
                }
            }
        }

        for(int i = row-2;i>=0;i--){
            for(int j=0;j<col;j++){
                if (mine[i][j]==0){
                    continue;
                }
                if(j!=0){
                    leftDownDp[i][j] = leftDownDp[i+1][j-1]+1;
                }
                if(j!=col-1){
                    rightDownDp[i][j] = rightDownDp[i+1][j+1]+1;
                }
            }
        }
        int maxSize = 1; // 마지막에 0 인 경우도 고려해 주어야 함
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                int sizeLimit = Math.min(rightUpDp[i][j],rightDownDp[i][j]);
                for(int k=maxSize+1;k<=sizeLimit;k++){
                    if(j+2*(k-1)<col){
                        if(leftUpDp[i][j+2*(k-1)]>=k&&leftDownDp[i][j+2*(k-1)]>=k){
                            maxSize = k;
                        }
                    }
                }
            }
        }


        System.out.println(maxSize);
    }
}
