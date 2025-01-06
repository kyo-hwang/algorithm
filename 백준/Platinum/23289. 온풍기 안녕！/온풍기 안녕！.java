import java.io.BufferedReader;
import java.io.IOError;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main{
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;

    //row col
    public static final int[] dRow = {0,0,0,-1,1};
    public static final int[] dCol = {0,1,-1,0,0};
    public static final List<List<Integer>> RIGHT_CHECK = Arrays.asList(Arrays.asList(RIGHT),Arrays.asList(DOWN,RIGHT),Arrays.asList(UP,RIGHT));

    public static final List<List<Integer>> LEFT_CHECK = Arrays.asList(Arrays.asList(LEFT),Arrays.asList(DOWN,LEFT),Arrays.asList(UP,LEFT));

    public static final List<List<Integer>> UP_CHECK = Arrays.asList(Arrays.asList(UP),Arrays.asList(LEFT,UP),Arrays.asList(RIGHT,UP));

    public static final List<List<Integer>> DOWN_CHECK = Arrays.asList(Arrays.asList(DOWN),Arrays.asList(LEFT,DOWN),Arrays.asList(RIGHT,DOWN));

    public static int rowSize;
    public static int colSize;

    public static int t;
    public static int[][] temperatureStates;

    //벽이 바깥과 되어 있는 경우는 없겠지?
    public static boolean[][][] isBlocked;

    public static int[][] map;

    public static List<List<Integer>> checkBlocks = new ArrayList<>();

    public static class Heater{
        int row;
        int col;
        int direction;

        public Heater(int row,int col,int direction){
            this.row = row;
            this.col = col;
            this.direction = direction;
        }

        public void operate(){
            Deque<List<Integer>> que = new ArrayDeque<>();
            Set<List<Integer>> visitedLoc = new HashSet<>();

            List<List<Integer>> check;
            if(direction ==LEFT){
                check = LEFT_CHECK;
            } else if (direction==RIGHT) {
                check = RIGHT_CHECK;
            } else if (direction==UP) {
                check = UP_CHECK;
            }else{
                check = DOWN_CHECK;
            }
            //온풍기가 나오는 방향에 칸은 항상 존재한다.
            que.add(new ArrayList<>(Arrays.asList(row+dRow[direction],col+dCol[direction],5)));
            temperatureStates[row+dRow[direction]][col+dCol[direction]]+=5;
            visitedLoc.add(new ArrayList<>(Arrays.asList(row+dRow[direction],col+dCol[direction])));

            while (!que.isEmpty()){
                List<Integer> beforeInfo = que.poll();
                int temperatureUp = beforeInfo.get(2)-1;
                if(temperatureUp==0){
                    continue;
                }
                for(List<Integer> move:check){
                    List<Integer> nextLoc = calNextLoc(beforeInfo.get(0),beforeInfo.get(1),move,visitedLoc);
                    if(nextLoc == null){
                        continue;
                    }
                    visitedLoc.add(nextLoc);
                    temperatureStates[nextLoc.get(0)][nextLoc.get(1)]+=temperatureUp;
                    que.add(Arrays.asList(nextLoc.get(0),nextLoc.get(1),temperatureUp));
                }
            }
        }

        private List<Integer> calNextLoc(int nextRow,int nextCol,List<Integer> move,Set<List<Integer>> visitedLoc){
            for(int direct:move){
                if(isBlocked[nextRow][nextCol][direct]){
                    return null;
                }
                nextRow = nextRow+dRow[direct];
                nextCol = nextCol+dCol[direct];
                if(nextRow<0||nextRow>rowSize-1){
                    return null;
                }
                if(nextCol<0||nextCol>colSize-1){
                    return null;
                }
            }
            if(visitedLoc.contains(Arrays.asList(nextRow,nextCol))){
                return null;
            }
            return Arrays.asList(nextRow,nextCol);
        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rowSize = input[0];
        colSize = input[1];
        t = input[2];

        map = new int[rowSize][colSize];

        for(int i=0;i<rowSize;i++){
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        temperatureStates = new int[rowSize][colSize];

        List<Heater> heaters = new ArrayList<>();

        for(int i=0;i<rowSize;i++){
            for(int j=0;j<colSize;j++){

                int blockInfo = map[i][j];
                if(blockInfo>=1&&blockInfo<=4){
                    heaters.add(new Heater(i,j,blockInfo));
                }
                else if(blockInfo==5){
                    checkBlocks.add(Arrays.asList(i,j));
                }
            }
        }

        isBlocked = new boolean[rowSize][colSize][5];
        int n = Integer.parseInt(bf.readLine());
        for(int i=0;i<n;i++){
            input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int sideRow=input[0]-1;
            int sideCol=input[1]-1;
            if(input[2]==0){
                sideRow --;
            } else if (input[2]==1) {
                sideCol ++;
            }

            if(sideRow<0||sideRow>=rowSize||sideCol<0||sideCol>=colSize){
                continue;
            }
            if(input[0]-1<0||input[0]-1>=rowSize||input[1]-1<0||input[1]-1>=colSize){
                continue;
            }

            if(input[2]==0){
                isBlocked[input[0]-1][input[1]-1][UP] =true;
                isBlocked[sideRow][sideCol][DOWN] =true;
            } else if (input[2]==1) {
                isBlocked[input[0]-1][input[1]-1][RIGHT] =true;
                isBlocked[sideRow][sideCol][LEFT] =true;
            }
        }


        int[][] stateChanged;
        for(int i=1;i<=100;i++){
            stateChanged = new int[rowSize][colSize];
            //히터켜기
            for(Heater heater:heaters){
                heater.operate();
            }
            //온조 조절
            for(int j=0;j<rowSize;j++){
                for(int k=0;k<colSize;k++){
                    for(int a=1;a<5;a++){
                        int nextRow = j+dRow[a];
                        int nextCol = k+dCol[a];
                        if(nextRow<0||nextRow>=rowSize||nextCol<0||nextCol>=colSize){
                            continue;
                        }
                        if(isBlocked[j][k][a]){
                            continue;
                        }
                        int difference = temperatureStates[j][k] - temperatureStates[nextRow][nextCol];
                        if(difference>0){
                            stateChanged[j][k] -=difference/4;
                            stateChanged[nextRow][nextCol] +=difference/4;
                        }
                    }
                }
            }


            for(int j=0;j<rowSize;j++){
                for(int k=0;k<colSize;k++){
                    temperatureStates[j][k] += stateChanged[j][k];
                }
            }

            //바깥온도 감소
            for(int j=0;j<rowSize;j++){
                if(j!=0&&j!=rowSize-1){
                    temperatureStates[j][0] = Math.max(temperatureStates[j][0]-1,0);
                    temperatureStates[j][colSize-1] = Math.max(temperatureStates[j][colSize-1]-1,0);
                    continue;
                }
                for(int k=0;k<colSize;k++){
                    temperatureStates[j][k] = Math.max(temperatureStates[j][k]-1,0);
                }
            }


            if(checkWarmEnough()){
                System.out.println(i);
                return;
            }
        }
        System.out.println(101);
    }

    public static boolean checkWarmEnough(){
        for(List<Integer> checkBlock:checkBlocks){
            if(temperatureStates[checkBlock.get(0)][checkBlock.get(1)]<t){
                return false;
            }
        }
        return true;
    }
}
