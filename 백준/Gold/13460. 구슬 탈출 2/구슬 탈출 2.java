import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int row;
    private static int col;
    private static String[][] map;
    private static int[] dRow = {0,1,0,-1};
    private static int[] dCol = {1,0,-1,0};
    public static class Marble{
        @Override
        public String toString() {
            return "(row: "+row+" col: "+col+")";
        }

        public Marble(int row, int col){
            this.row = row;
            this.col = col;
        }
        private int row;
        private int col;

        public int getRow(){
            return row;
        }

        public int getCol(){
            return col;
        }

        public void setRow(int row){
            this.row = row;
        }

        public void setCol(int col){
            this.col = col;
        }

        //0 right, 1 bottom, 2 left, 3 top
        public Marble move(String[][] map,int direction){
            int i=1;
            if(direction == 0){
                while(!map[row][col+i].equals("#")){
                    if(map[row][col+i].equals("O")){
                        return null;
                    }
                    i++;
                }
                return new Marble(row,col+i-1);
            }
            else if(direction == 1){
                while(!map[row+i][col].equals("#")){
                    if(map[row+i][col].equals("O")){
                        return null;
                    }
                    i++;
                }
                return new Marble(row+i-1,col);
            } else if (direction == 2) {
                while (!map[row][col-i].equals("#")){
                    if(map[row][col-i].equals("O")){
                        return null;
                    }
                    i++;
                }
                return new Marble(row,col-i+1);
            }
            else{
                while (!map[row-i][col].equals("#")){
                    if(map[row-i][col].equals("O")){
                        return null;
                    }
                    i++;
                }
                return new Marble(row-i+1,col);
            }
        }

        public boolean equals(Marble marble){
            if(this.row == marble.getRow() && this.col == marble.getCol()){
                return true;
            }
            return false;
        }
    }
    public static class Point{
        @Override
        public String toString() {
            return "red: "+red.toString()+" blue: "+blue.toString()+" time: "+time;
        }

        public Point(Marble red, Marble blue, int time){
            this.red = red;
            this.blue = blue;
            this.time = time;
            this.map = Main.map;
        }
        private String[][] map;
        private Marble red;
        private Marble blue;
        private int time;

        public int getTime(){
            return time;
        }

        public List<Point> findPossiblePoint(){
            List<Point> points = new ArrayList<>();
            if(time==10){
                return points;
            }
            for(int i=0;i<4;i++){
                Marble nextRed = red.move(map,i);
                Marble nextBlue = blue.move(map,i);
                if(nextBlue==null){
                    continue;
                }
                if(nextRed==null){
                    return null;
                }
                else if(nextBlue!=null){
                    points.add(new Point(nextRed,nextBlue,time+1));
                    if(nextRed.equals(nextBlue)){
                        if(i==0){
                            if(red.getCol()<blue.getCol()){
                                nextRed.setCol(nextRed.getCol()-1);
                            }
                            else {
                                nextBlue.setCol(nextBlue.getCol()-1);
                            }
                        }
                        if(i==1){
                            if(red.getRow()<blue.getRow()){
                                nextRed.setRow(nextRed.getRow()-1);
                            }
                            else {
                                nextBlue.setRow(nextBlue.getRow()-1);
                            }
                        }
                        if(i==2){
                            if(red.getCol()<blue.getCol()){
                                nextBlue.setCol(nextBlue.getCol()+1);
                            }
                            else {
                                nextRed.setCol(nextRed.getCol()+1);
                            }
                        }
                        if(i==3){
                            if(red.getRow()<blue.getRow()){
                                nextBlue.setRow(nextBlue.getRow()+1);
                            }
                            else {
                                nextRed.setRow(nextRed.getRow()+1);
                            }
                        }
                    }
                }
            }
            return points;
        }
    }



    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] c = Arrays.stream(bf.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        row = c[0];
        col = c[1];

        map = new String[row][col];
        for(int i=0;i<row;i++){
            map[i] = bf.readLine().split("");
//            System.out.println(Arrays.toString(map[i]));
        }

        Marble red = new Marble(0,0);
        Marble blue = new Marble(0,0);;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(map[i][j].equals("R")){
                    red = new Marble(i,j);
                } else if (map[i][j].equals("B")) {
                    blue = new Marble(i,j);
                }
            }
        }
//        System.out.println(red +""+ blue);

        int result = -2;

        Deque<Point> pointDeque = new ArrayDeque<>();
        pointDeque.offer(new Point(red,blue,0));
        while (!pointDeque.isEmpty()){
            Point point = pointDeque.poll();
            List<Point> nextPoints = point.findPossiblePoint();
//            System.out.println(nextPoints);
            if(nextPoints==null){
                result = point.getTime();
                break;
            }
            for(Point nextPoint:nextPoints){
                pointDeque.offer(nextPoint);
            }
        }

        System.out.println(result+1);
    }
}
