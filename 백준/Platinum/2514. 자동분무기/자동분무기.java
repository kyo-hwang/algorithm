import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int generalProduction = Integer.parseInt(bf.readLine());
        int sprayerNumber = Integer.parseInt(bf.readLine());
        int[][] plant = new int[8][8];
        for(int i=0;i<8;i++){
            plant[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(input->Integer.parseInt(input)-generalProduction).toArray();
        }
        int fertilizerNum = calFertilizerNum(plant,sprayerNumber);
        int herbicideNum = sprayerNumber - fertilizerNum;

        int[] colDifference = calColDifference(plant,fertilizerNum,herbicideNum);
        int[] rowDifference = calRowDifference(plant,fertilizerNum,herbicideNum);

        char[][] map = new char[8][8];
        for(int i=0;i<8;i++){
            Arrays.fill(map[i],'.');
        }

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                int difference = plant[i][j] - colDifference[j];
                if(difference<rowDifference[i]){
                    map[i][j] = '+';
                } else if (difference>rowDifference[i]) {
                    map[i][j] = '-';
                }
            }
        }

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static int[] calRowDifference(int[][] plant,int fertilizerNum,int herbicideNum){
        int[] rowDifference = new int[8];
        for(int i=0;i<8;i++){
            int sum=0;
            for(int j=0;j<8;j++){
                sum+=plant[i][j];
            }
            rowDifference[i] = (sum-fertilizerNum+herbicideNum)/7;
        }
        return rowDifference;
    }
    public static int[] calColDifference(int[][] plant,int fertilizerNum, int herbicideNum){
        int[] colDifference = new int[8];
        for(int i=0;i<8;i++){
            int sum = 0;
            for(int j=0;j<8;j++){
                sum+=plant[j][i];
            }
            colDifference[i] = (sum-fertilizerNum+herbicideNum)/7;
        }
        return colDifference;
    }
    public static int calFertilizerNum(int[][] plant,int sprayNumber){
        int total = 0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                total +=plant[i][j];
            }
        }
        return (sprayNumber+total/15)/2;
    }
}
