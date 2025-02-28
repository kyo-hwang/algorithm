import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static int rs;
    public static int cs;
    public static int t;
    public static int[][] map;
    public static int upCleanerLoc=-1;
    public static int downCleanerLoc=-1;
    public static int[] dRow = {0,1,0,-1};
    public static int[] dCol = {1,0,-1,0};
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rs = input[0];
        cs = input[1];
        t = input[2];
        map = new int[rs][cs];
        for(int i=0;i<rs;i++){
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for(int i=0;i<rs;i++){
            if(map[i][0]==-1){
                upCleanerLoc = i;
                downCleanerLoc = i+1;
                break;
            }
        }

        for(int i=0;i<t;i++){
            timeGo();
        }
        int result = 0;

        for(int i=0;i<rs;i++){
            for(int j=0;j<cs;j++){
                if(map[i][j]==-1){
                    continue;
                }
                result+=map[i][j];
            }
        }

        System.out.println(result);
    }
    public static void timeGo(){
        int[][] tempMap = new int[rs][cs];
        tempMap[upCleanerLoc][0] = -1;
        tempMap[downCleanerLoc][0] = -1;
        for(int i=0;i<rs;i++){
            for(int j=0;j<cs;j++){
                if(map[i][j]==-1){
                    continue;
                }
                int amountMoved = map[i][j]/5;
                int movedCount = 0;
                for(int k=0;k<4;k++){
                    int nearRow = i+dRow[k];
                    int nearCol = j+dCol[k];
                    if(nearRow<0||nearRow>=rs||nearCol<0||nearCol>=cs){
                        continue;
                    }
                    if(map[nearRow][nearCol]==-1){
                        continue;
                    }
                    tempMap[nearRow][nearCol] +=amountMoved;
                    movedCount ++;
                }
                tempMap[i][j]+=map[i][j]-amountMoved*movedCount;
            }
        }
        

        for(int i=upCleanerLoc-1;i>=1;i--){
            tempMap[i][0] = tempMap[i-1][0];
        }
        for(int i=0;i<=cs-2;i++){
            tempMap[0][i] = tempMap[0][i+1];
        }
        for(int i=0;i<=upCleanerLoc-1;i++){
            tempMap[i][cs-1] = tempMap[i+1][cs-1];
        }
        for(int i=cs-1;i>=2;i--){
            tempMap[upCleanerLoc][i] = tempMap[upCleanerLoc][i-1];
        }
        tempMap[upCleanerLoc][1] = 0;


        for(int i=downCleanerLoc+1;i<=rs-2;i++){
            tempMap[i][0] = tempMap[i+1][0];
        }
        for(int i=0;i<=cs-2;i++){
            tempMap[rs-1][i] = tempMap[rs-1][i+1];
        }
        for(int i=rs-1;i>=downCleanerLoc+1;i--){
            tempMap[i][cs-1] = tempMap[i-1][cs-1];
        }
        for(int i=cs-1;i>=2;i--){
            tempMap[downCleanerLoc][i] = tempMap[downCleanerLoc][i-1];
        }
        tempMap[downCleanerLoc][1] = 0;

        map = tempMap;
    }
}
