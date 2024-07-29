import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static class Point {
        private int height;
        private int row;
        private int col;

        public Point(int height, int row, int col){
            this.height = height;
            this.row = row;
            this.col = col;
        }

        public int getHeight(){
            return height;
        }
        public int getRow(){
            return row;
        }
        public int getCol(){
            return col;
        }

        @Override
        public String toString() {
            return String.format("%s %s %s",height,row,col);
        }
    }
    public static int row;
    public static int col;
    public static int[][] map;
    public static int[][] possibleRoute;
    public static List<Point> points;
    public static int[] dRow = {0,1,0,-1};
    public static int[] dCol = {1,0,-1,0};

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] size = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        row = size[0];
        col = size[1];

        map = new int[row][col];
        possibleRoute = new int[row][col];
        points = new ArrayList<>();
        for(int i=0;i<row;i++){
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j=0;j<col;j++){
                points.add(new Point(map[i][j],i,j));
            }
        }
        possibleRoute[row-1][col-1] = 1;

//        for(int i=0;i<row;i++){
//            System.out.println(Arrays.toString(map[i]));
//        }

//        System.out.println(points);
        points.sort((e1,e2)->{return e1.height-e2.height;});
//        System.out.println(points);


        int destHeight = map[row-1][col-1];
        int i;
        for(i=0;i<row*col;i++){
            if(destHeight< points.get(i).getHeight()){
                break;
            }
        }

        for(int j=i;j<row*col;j++){
            calPossibleRoute(points.get(j));
        }

//        for(int k=0;k<row;k++){
//            System.out.println(Arrays.toString(possibleRoute[k]));
//        }

        System.out.println(possibleRoute[0][0]);
    }

    public static void calPossibleRoute(Point point){
        int sum = 0;
        for(int i=0;i<4;i++){
            int nextRow = point.getRow()+dRow[i];
            int nextCol = point.getCol()+dCol[i];
            if(nextRow>=0&&nextRow<row&&nextCol>=0&&nextCol<col){
                if(point.getHeight()>map[nextRow][nextCol]){
                    sum += possibleRoute[nextRow][nextCol];
                }
//                System.out.println(map[nextRow][nextCol]);
            }
//            System.out.println(nextRow+" "+nextCol+" ");
//            System.out.println(String.format("row : %s, col : %s, height : %s, sum : %s",point.getRow(),point.getCol(),point.getHeight(),sum));
        }
//        for(int k=0;k<row;k++){
//            System.out.println(Arrays.toString(possibleRoute[k]));
//        }

        possibleRoute[point.getRow()][point.getCol()] = sum;
    }
}
