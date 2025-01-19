import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static int row;
    public static int col;
    public static int[][] office;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        row = input[0];
        col = input[1];
        office = new int[row][col];
        for(int i=0;i<row;i++){
            office[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(calMinBlackArea());
    }

    public static int calMinBlackArea(){
        List<List<Integer>> cctvLocs = new ArrayList<>();
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(office[i][j]>=1&&office[i][j]<=5){
                    cctvLocs.add(Arrays.asList(i,j));
                }
            }
        }
        return back(cctvLocs,0,office);
    }

    public static int back(List<List<Integer>> cctvLocs,int curCheckCctv,int[][] office){

        if(curCheckCctv>=cctvLocs.size()||cctvLocs.isEmpty()){
            int blockArea = 0;
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    if(office[i][j]==0){
                        blockArea++;
                    }
                }
            }
            return blockArea;
        }
        int min=Integer.MAX_VALUE;
        int curRow = cctvLocs.get(curCheckCctv).get(0);
        int curCol = cctvLocs.get(curCheckCctv).get(1);
        if(office[curRow][curCol]==1){
            for(int i=1;i<5;i++){
                int[][] newOffice = copySecondArray(office);
                fillOffice(newOffice,curRow,curCol, List.of(i));
                min = Math.min(back(cctvLocs,curCheckCctv+1,newOffice),min);
            }
        }
        else if(office[curRow][curCol]==2){
            for(int i=1;i<3;i++){
                int[][] newOffice = copySecondArray(office);
                fillOffice(newOffice,curRow,curCol,List.of(i,i+2));
                min = Math.min(back(cctvLocs,curCheckCctv+1,newOffice),min);
            }
        }
        else if(office[curRow][curCol]==3){
            for(int i=1;i<5;i++){
                int[][] newOffice = copySecondArray(office);
                int next = (i+3)%5;
                if(i!=1){
                    next+=1;
                }
                fillOffice(newOffice,curRow,curCol,List.of(i,next));
                min  = Math.min(back(cctvLocs,curCheckCctv+1,newOffice),min);
            }
        }else if(office[curRow][curCol]==4){
            List<Integer> candidates = List.of(1,2,3,4);
            for(int i=0;i<4;i++){
                int[][] newOffice = copySecondArray(office);
                List<Integer> possibleCandidates = new ArrayList<>(candidates);
                possibleCandidates.remove(i);
                fillOffice(newOffice,curRow,curCol,possibleCandidates);
                min = Math.min(back(cctvLocs,curCheckCctv+1,newOffice),min);
            }
        }else if(office[curRow][curCol]==5){
            int[][] newOffice = copySecondArray(office);
            List<Integer> possibleCandidates = List.of(1,2,3,4);
            fillOffice(newOffice,curRow,curCol,possibleCandidates);
            min = Math.min(back(cctvLocs,curCheckCctv+1,newOffice),min);
        }
        return min;
    }

    public static void fillOffice(int[][] office,int curRow,int curCol,List<Integer> directions){
        if(directions.contains(1)){//down
            for(int i=curRow+1;i<row;i++){
                if(office[i][curCol]==6){
                    break;
                }
                if(office[i][curCol]!=0){
                    continue;
                }
                office[i][curCol] = 7;
            }
        }
        if(directions.contains(2)){//left
            for(int i=curCol-1;i>=0;i--){
                if(office[curRow][i]==6){
                    break;
                }
                if(office[curRow][i]!=0){
                    continue;
                }
                office[curRow][i] = 7;
            }
        }
        if(directions.contains(3)){//up
            for(int i=curRow-1;i>=0;i--){
                if(office[i][curCol]==6){
                    break;
                }
                if(office[i][curCol]!=0){
                    continue;
                }
                office[i][curCol] = 7;
            }
        }
        if(directions.contains(4)){//right
            for(int i=curCol+1;i<col;i++){
                if(office[curRow][i]==6){
                    break;
                }
                if(office[curRow][i]!=0){
                    continue;
                }
                office[curRow][i] = 7;
            }
        }
    }

    public static int[][] copySecondArray(int[][] array){
        int[][] newArray = new int[array.length][array[0].length];
        for(int i=0;i<array.length;i++){
            newArray[i] = Arrays.copyOf(array[i],col);
        }
        return newArray;
    }
}
