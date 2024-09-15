import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int row;
    public static int col;
    public static int m;

    public static HashMap<Integer,int[]> sharksAlive;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        row = input[0];
        col = input[1];
        m = input[2];

        //r,c,속력,이동방향,크기
        sharksAlive = new HashMap<>();

        int fishNearest = row+1;
        int fish = -1;

        for(int i=0;i<m;i++) {
            sharksAlive.put(i, Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());

            if(sharksAlive.get(i)[1]==1&&sharksAlive.get(i)[0]<fishNearest){
                fishNearest = sharksAlive.get(i)[0];
                fish = i;
            }
        }

        int result = 0;

        for(int i=1;i<=col;i++){
            if(fish!=-1){
                result+=sharksAlive.get(fish)[4];
                sharksAlive.remove(fish);
            }
            if(i==col){
                break;
            }

            int[][] oceanState = moveSharks();
            fish = findNearestFishId(i+1,oceanState);

        }

        System.out.println(result);
    }

    public static int findNearestFishId(int i,int[][] oceanState){
        for(int j=1;j<=row;j++){
            if(oceanState[j][i]!=-1){
                return oceanState[j][i];
            }
        }
        return -1;
    }

    public static int[][] moveSharks(){
        int[][] ocean = new int[row+1][col+1];
        for(int j=0;j<row+1;j++){
            Arrays.fill(ocean[j],-1);
        }
        List<Integer> sharksToRemove = new ArrayList<>();
        for(int sharkId:sharksAlive.keySet()){
            int[] shark = sharksAlive.get(sharkId);
            if(shark[3]==1||shark[3]==2){
                moveVertical(shark);
            }
            else{
                moveHorizontal(shark);
            }

            if(ocean[shark[0]][shark[1]]==-1){
                ocean[shark[0]][shark[1]] = sharkId;
            }
            else{
                int curSharkW = shark[4];
                int[] beforeShark = sharksAlive.get(ocean[shark[0]][shark[1]]);
                int beforeSharkW = beforeShark[4];

                if(curSharkW>beforeSharkW){
                    sharksToRemove.add(ocean[shark[0]][shark[1]]);
                    ocean[shark[0]][shark[1]] = sharkId;
                }
                else{
                    sharksToRemove.add(sharkId);
                }
            }
        }
        //순회하면서 리스트 제거하는 방법 알아보기
        for(int sharkId:sharksToRemove){
            sharksAlive.remove(sharkId);
        }

        return ocean;
    }

    public static void moveVertical(int[] shark){
        int k = shark[2]%(2*row-2);
        int d = shark[3];
        int curRow = shark[0];
        while(true) {
            if (d == 1) {
                if (curRow - k > 1) {
                    curRow = curRow - k;
                    break;
                } else {
                    k -= (curRow - 1);
                    curRow = 1;
                    d = 2;
                }
                continue;
            }

            if (curRow + k < row) {
                curRow = curRow + k;
                break;
            } else {
                k -= (row - curRow);
                curRow = row;
                d = 1;
            }

        }
        shark[0] = curRow;
        shark[3] = d;
    }

    public static void moveHorizontal(int[] shark){
        int k = shark[2]%(2*col-2);
        int d = shark[3];
        int curCol = shark[1];
        while(true){
            if(d==3){
                if(curCol+k<col){
                    curCol = curCol+k;
                    break;
                }
                k -= (col-curCol);
                curCol = col;
                d = 4;
                continue;
            }
            if(curCol-k>1){
                curCol = curCol-k;
                break;
            }
            k -=(curCol-1);
            curCol = 1;
            d = 3;
        }
        shark[1] = curCol;
        shark[3] = d;
    }

}
