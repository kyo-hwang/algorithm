import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public final static String LEFT = "L";
    public final static String RIGHT = "R";
    public final static String DOWN = "D";
    public final static String UP = "U";
    public static int rowLength;
    public static int colLength;
    public static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rowLength = input[0];
        colLength = input[1];

        map = new int[rowLength][colLength];
        for(int i=0;i<rowLength;i++){
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        if(rowLength%2==1){
            System.out.println(calHappiestRootRowOdd());
        }
        else if(colLength%2==1){
            System.out.println(calHappiestRootColOdd());
        }else{
            System.out.println(calHappiestAllEven());
        }
    }

    public static StringBuffer calHappiestAllEven(){
        int min = Integer.MAX_VALUE;
        int minRow = -1;
        int minCol = -1;
        for(int i=0;i<rowLength;i++){
            for(int j=0;j<colLength;j++){
                if((i+j)%2==0){
                    continue;
                }
                if(map[i][j]<min){
                    min = map[i][j];
                    minRow = i;
                    minCol = j;
                }
            }
        }
        StringBuffer result = new StringBuffer();

        StringBuffer routeRepeatFirst = createRightRow();
        routeRepeatFirst.append(createLeftRow());
        int repeat = minRow/2;
        for(int i=1;i<=repeat;i++){
            result.append(routeRepeatFirst);
        }
        int direction = 0;
        int curRow = repeat*2;
        int curCol = 0;
        for(int i=repeat*2;i<repeat*2+2;i++){
            for(int j=0;j<colLength;j++){
                if(i==repeat*2+1&&j==colLength-2){
                    break;
                }
                if(direction==0){
                    if(curRow+1==minRow&&curCol==minCol){
                        result.append(RIGHT);
                        curCol+=1;
                        continue;
                    }
                    curRow+=1;
                    result.append(DOWN);
                }
                else if(direction==1){
                    if(curRow==minRow&&curCol+1==minCol){
                        result.append(RIGHT);
                        curCol+=1;
                        continue;
                    }
                    curCol+=1;
                    result.append(RIGHT);
                }
                else if(direction==2){
                    if(curRow-1==minRow&&curCol==minCol){
                        result.append(RIGHT);
                        curCol+=1;
                        continue;
                    }
                    curRow-=1;
                    result.append(UP);
                }
                else if(direction==3){
                    if(curRow==minRow&&curCol+1==minCol){
                        result.append(RIGHT);
                        curCol+=1;
                        continue;
                    }
                    curCol+=1;
                    result.append(RIGHT);
                }
                direction=(direction+1)%4;
            }
        }
        result.append(DOWN);
        StringBuffer routeRepeatSecond = createLeftRow();
        routeRepeatSecond.append(createRightRow());
        for(int i=repeat+2;i<=rowLength/2;i++){
            result.append(routeRepeatSecond);
        }

        result.deleteCharAt(result.length()-1);

        return result;
    }

    public static StringBuffer calHappiestRootRowOdd(){
        StringBuffer routeRepeat = createRightRow();
        routeRepeat.append(createLeftRow());

        StringBuffer route = new StringBuffer();
        int repeat = rowLength/2;
        for(int i=1;i<=repeat;i++){
            route.append(routeRepeat);
        }
        route.append(createRightRow());

        route.deleteCharAt(route.length()-1);
        return route;
    }

    public static StringBuffer calHappiestRootColOdd(){
        StringBuffer routeRepeat = createDownCol();
        routeRepeat.append(createUpCol());

        StringBuffer route = new StringBuffer();
        int repeat = colLength/2;
        for(int i=1;i<=repeat;i++){
            route.append(routeRepeat);
        }
        route.append(createDownCol());

        route.deleteCharAt(route.length()-1);
        return route;
    }

    public static StringBuffer createDownCol(){
        return new StringBuffer(DOWN.repeat(rowLength-1)).append(RIGHT);
    }

    public static StringBuffer createUpCol(){
        return new StringBuffer(UP.repeat(rowLength-1)).append(RIGHT);
    }

    public static StringBuffer createRightRow(){
        return new StringBuffer(RIGHT.repeat(colLength-1)).append(DOWN);
    }

    public static StringBuffer createLeftRow(){
        return new StringBuffer(LEFT.repeat(colLength-1)).append(DOWN);
    }
}
