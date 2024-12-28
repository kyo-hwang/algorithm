

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static final Character WHILE = 'w';
    public static final Character YELLOW = 'y';
    public static final Character RED = 'r';
    public static final Character ORANGE = 'o';
    public static final Character GREEN = 'g';
    public static final Character BLUE = 'b';

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        for(int i=0;i<t;i++){
            int n = Integer.parseInt(bf.readLine());
            String[] order = bf.readLine().split(" ");
            simulate(n,order);
        }
    }
    public static void simulate(int n, String[] order){
        Character[][] upSide = createSide(WHILE);
        Character[][] downSide = createSide(YELLOW);
        Character[][] frontSide = createSide(RED);
        Character[][] backSide = createSide(ORANGE);
        Character[][] leftSide = createSide(GREEN);
        Character[][] rightSide = createSide(BLUE);


        for(String curRotate:order){
            if(curRotate.equals("U+")){
                rotateUpPlus(frontSide,rightSide,backSide,leftSide);
                upSide = rotateOneSide(upSide,'+');
            }
            else if(curRotate.equals("U-")){
                rotateUpMinus(frontSide,rightSide,backSide,leftSide);
                upSide = rotateOneSide(upSide,'-');
            }
            else if(curRotate.equals("D+")){
                rotateDownPlus(frontSide,rightSide,backSide,leftSide);
                downSide = rotateOneSide(downSide,'+');
            }
            else if(curRotate.equals("D-")){
                rotateDownMinus(frontSide,rightSide,backSide,leftSide);
                downSide = rotateOneSide(downSide,'-');
            }
            else if(curRotate.equals("F+")){
                rotateFrontPlus(upSide,rightSide,downSide,leftSide);
                frontSide = rotateOneSide(frontSide,'+');
            }
            else if(curRotate.equals("F-")){
                rotateFrontMinus(upSide,rightSide,downSide,leftSide);
                frontSide = rotateOneSide(frontSide,'-');
            }
            else if(curRotate.equals("B+")){
                rotateBackPlus(upSide,rightSide,downSide,leftSide);
                backSide = rotateOneSide(backSide,'-');
            }
            else if(curRotate.equals("B-")){
                rotateBackMinus(upSide,rightSide,downSide,leftSide);
                backSide = rotateOneSide(backSide,'+');
            }
            else if(curRotate.equals("L+")){
                rotateLeftPlus(frontSide,upSide,backSide,downSide);
                leftSide = rotateOneSide(leftSide,'-');
            }
            else if(curRotate.equals("L-")){
                rotateLeftMinus(frontSide,upSide,backSide,downSide);
                leftSide = rotateOneSide(leftSide,'+');
            }
            else if(curRotate.equals("R+")){
                rotateRightPlus(frontSide,upSide,backSide,downSide);
                rightSide = rotateOneSide(rightSide,'+');
            }
            else if(curRotate.equals("R-")){
                rotateRightMinus(frontSide,upSide,backSide,downSide);
                rightSide = rotateOneSide(rightSide,'-');
            }
//            System.out.println(curRotate);
////
//            System.out.println("up");
//            print(upSide);
//            System.out.println("down");
//            print(downSide);
//            System.out.println("left");
//            print(leftSide);
//            System.out.println("right");
//            print(rightSide);
//            System.out.println("front");
//            print(frontSide);
//            System.out.println("back");
//            print(backSide);
//
//
//            System.out.println();
        }

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(upSide[i][j]);
            }
            System.out.println();
        }
    }
    public static void print(Character[][] side){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(side[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void rotateFrontMinus(Character[][] upSide,Character[][] rightSide,Character[][] downSide,Character[][] leftSide){
        char f1 = leftSide[0][0];
        char f2 = leftSide[1][0];
        char f3 = leftSide[2][0];

        leftSide[0][0] = upSide[2][2];
        leftSide[1][0] = upSide[2][1];
        leftSide[2][0] = upSide[2][0];

        upSide[2][2] = rightSide[2][0];
        upSide[2][1] = rightSide[1][0];
        upSide[2][0] = rightSide[0][0];


        rightSide[0][0] = downSide[0][2];
        rightSide[1][0] = downSide[0][1];
        rightSide[2][0] = downSide[0][0];

        downSide[0][0] = f1;
        downSide[0][1] = f2;
        downSide[0][2] = f3;
    }
    public static void rotateFrontPlus(Character[][] upSide,Character[][] rightSide,Character[][] downSide,Character[][] leftSide){
        char f1 = leftSide[0][0];
        char f2 = leftSide[1][0];
        char f3 = leftSide[2][0];

        leftSide[0][0] = downSide[0][0];
        leftSide[1][0] = downSide[0][1];
        leftSide[2][0] = downSide[0][2];

        downSide[0][0] = rightSide[2][0];
        downSide[0][1] = rightSide[1][0];
        downSide[0][2] = rightSide[0][0];

        rightSide[0][0] = upSide[2][0];
        rightSide[1][0] = upSide[2][1];
        rightSide[2][0] = upSide[2][2];

        upSide[2][2] = f1;
        upSide[2][1] = f2;
        upSide[2][0] = f3;
    }


    public static void rotateBackPlus(Character[][] upSide,Character[][] rightSide,Character[][] downSide,Character[][] leftSide){
        char f3 = leftSide[0][2];
        char s3 = leftSide[1][2];
        char t3 = leftSide[2][2];

        leftSide[2][2] = upSide[0][0];
        leftSide[1][2] = upSide[0][1];
        leftSide[0][2] = upSide[0][2];

        upSide[0][0] = rightSide[0][2];
        upSide[0][1] = rightSide[1][2];
        upSide[0][2] = rightSide[2][2];

        rightSide[0][2] = downSide[2][2];
        rightSide[1][2] = downSide[2][1];
        rightSide[2][2] = downSide[2][0];

        downSide[2][2] = t3;
        downSide[2][1] = s3;
        downSide[2][0] = f3;
    }

    public static void rotateBackMinus(Character[][] upSide,Character[][] rightSide,Character[][] downSide,Character[][] leftSide){
        char f3 = leftSide[0][2];
        char s3 = leftSide[1][2];
        char t3 = leftSide[2][2];

        leftSide[2][2] = downSide[2][2];
        leftSide[1][2] = downSide[2][1];
        leftSide[0][2] = downSide[2][0];

        downSide[2][0] = rightSide[2][2];
        downSide[2][1] = rightSide[1][2];
        downSide[2][2] = rightSide[0][2];

        rightSide[2][2] = upSide[0][2];
        rightSide[1][2] = upSide[0][1];
        rightSide[0][2] = upSide[0][0];

        upSide[0][2] = f3;
        upSide[0][1] = s3;
        upSide[0][0] = t3;
    }

    public static void rotateUpPlus(Character[][] frontSide,Character[][] rightSide,Character[][] backSide,Character[][] leftSide){
        Character[][][] sides = new Character[4][3][3];
        sides[0] = leftSide;
        sides[1] = backSide;
        sides[2] = rightSide;
        sides[3] = frontSide;

        rotateRow(sides,0);
        //2
        //0
        char tmp = rightSide[0][0];
        rightSide[0][0] = rightSide[0][2];
        rightSide[0][2] = tmp;

        tmp = leftSide[0][0];
        leftSide[0][0] = leftSide[0][2];
        leftSide[0][2] = tmp;
    }

    public static void rotateUpMinus(Character[][] frontSide,Character[][] rightSide,Character[][] backSide,Character[][] leftSide){
        Character[][][] sides = new Character[4][3][3];
        sides[0] = frontSide;
        sides[1] = rightSide;
        sides[2] = backSide;
        sides[3] = leftSide;

        rotateRow(sides,0);

        char tmp = frontSide[0][0];
        frontSide[0][0] = frontSide[0][2];
        frontSide[0][2] = tmp;

        tmp = backSide[0][0];
        backSide[0][0] = backSide[0][2];
        backSide[0][2] = tmp;
    }

    public static void rotateDownPlus(Character[][] frontSide,Character[][] rightSide,Character[][] backSide,Character[][] leftSide){
        Character[][][] sides = new Character[4][3][3];
        sides[0] = frontSide;
        sides[1] = rightSide;
        sides[2] = backSide;
        sides[3] = leftSide;

        rotateRow(sides,2);

        char tmp = frontSide[2][0];
        frontSide[2][0] = frontSide[2][2];
        frontSide[2][2] = tmp;

        tmp = backSide[2][0];
        backSide[2][0] = backSide[2][2];
        backSide[2][2] = tmp;
    }

    public static void rotateDownMinus(Character[][] frontSide,Character[][] rightSide,Character[][] backSide,Character[][] leftSide){
        Character[][][] sides = new Character[4][3][3];
        sides[0] = frontSide;
        sides[1] = leftSide;
        sides[2] = backSide;
        sides[3] = rightSide;

        rotateRow(sides,2);

        char tmp = rightSide[2][0];
        rightSide[2][0] = rightSide[2][2];
        rightSide[2][2] = tmp;

        tmp = leftSide[2][0];
        leftSide[2][0] = leftSide[2][2];
        leftSide[2][2] = tmp;
    }


    public static void rotateRightPlus(Character[][] frontSide,Character[][] upSide,Character[][] backSide,Character[][] downSide){
        Character[][][] sides = new Character[4][3][3];
        sides[0] = downSide;
        sides[1] = frontSide;
        sides[2] = upSide;
        sides[3] = backSide;

        rotateCol(sides,2);

        char tmp = backSide[0][2];
        backSide[0][2] = backSide[2][2];
        backSide[2][2] = tmp;

        tmp = downSide[2][2];
        downSide[2][2] = downSide[0][2];
        downSide[0][2] = tmp;
    }

    public static void rotateRightMinus(Character[][] frontSide,Character[][] upSide,Character[][] backSide,Character[][] downSide){
        Character[][][] sides = new Character[4][3][3];
        sides[0] = upSide;
        sides[1] = frontSide;
        sides[2] = downSide;
        sides[3] = backSide;

        rotateCol(sides,2);

        char tmp = backSide[0][2];
        backSide[0][2] = backSide[2][2];
        backSide[2][2] = tmp;

        tmp = upSide[2][2];
        upSide[2][2] = upSide[0][2];
        upSide[0][2] = tmp;
    }

    public static void rotateLeftPlus(Character[][] frontSide,Character[][] upSide,Character[][] backSide,Character[][] downSide){
        Character[][][] sides = new Character[4][3][3];
        sides[0] = upSide;
        sides[1] = frontSide;
        sides[2] = downSide;
        sides[3] = backSide;

        rotateCol(sides,0);

        char tmp = backSide[0][0];
        backSide[0][0] = backSide[2][0];
        backSide[2][0] = tmp;

        tmp = upSide[2][0];
        upSide[2][0] = upSide[0][0];
        upSide[0][0] = tmp;
    }

    public static void rotateLeftMinus(Character[][] frontSide,Character[][] upSide,Character[][] backSide,Character[][] downSide){
        Character[][][] sides = new Character[4][3][3];
        sides[0] = downSide;
        sides[1] = frontSide;
        sides[2] = upSide;
        sides[3] = backSide;

        rotateCol(sides,0);

        char tmp = backSide[0][0];
        backSide[0][0] = backSide[2][0];
        backSide[2][0] = tmp;

        tmp = downSide[2][0];
        downSide[2][0] = downSide[0][0];
        downSide[0][0] = tmp;
    }


    public static void rotateCol(Character[][][] sides,int col){
        Character[] lastSideRow = new Character[3];
        for(int i=0;i<3;i++){
            lastSideRow[i] = sides[3][i][col];
        }
        for(int i=3;i>=1;i--){
            for(int j=0;j<3;j++){
                sides[i][j][col] = sides[i-1][j][col];
            }
        }
        for(int j=0;j<3;j++){
            sides[0][j][col] = lastSideRow[j];
        }
//        char temp = sides[3][0][col];
//        sides[3][0][col] = sides[3][2][col];
//        sides[3][2][col] = temp;
//
//        temp = sides[0][0][col];
//        sides[0][0][col] = sides[0][2][col];
//        sides[0][2][col] = temp;
    }

    public static void rotateRow(Character[][][] sides,int row){
        Character[] lastSideRow = sides[3][row];
        for(int i=3;i>=1;i--){
            sides[i][row] = sides[i-1][row];
        }
        sides[0][row] = lastSideRow;
    }

    public static Character[][] rotateOneSide(Character[][] side,Character direction){
        Character[][] newSide = new Character[3][3];
        if(direction=='+'){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    newSide[j][2-i] = side[i][j];
                }
            }
            return newSide;
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                newSide[2-j][i] = side[i][j];
            }
        }
        return newSide;
    }
    public static Character[][] createSide(Character color){
        Character[][] side = new Character[3][3];
        for(int i=0;i<3;i++){
            Arrays.fill(side[i],color);
        }
        return side;
    }
}
