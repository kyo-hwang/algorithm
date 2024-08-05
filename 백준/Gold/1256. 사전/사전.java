import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static Integer MAX = 1000000000;
    private static long[][] combinationTable;
    private static int k;
    private static int n;
    private static int m;
    private static StringBuffer result;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] commands = Arrays.stream(bf.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        n = commands[0];
        m = commands[1];
        result = new StringBuffer("a".repeat(n+m));
        k = commands[2];

        combinationTable = new long[101][201];

        Arrays.fill(combinationTable[0],1);
//        Arrays.fill(combinationTable[1],1);

        for(int i=1;i<101;i++){
            for(int j=i;j<201;j++){
                if(combinationTable[i-1][j-1]>MAX){
                    combinationTable[i-1][j-1] = MAX+1;
                }
                combinationTable[i][j] = combinationTable[i-1][j-1]*j/i;
                if(combinationTable[i][j]>MAX){
                    combinationTable[i][j] = MAX+1;
                }
//                System.out.println(Arrays.toString(combinationTable[i]));
            }
        }

//        System.out.println(combinationTable[2][3]);

        if(!getString(n+m,m,0)){
            System.out.println(-1);
        }
        else{
            System.out.println(result);
        }

//        System.out.println(result);

    }

    public static boolean getString(int x,int y,int from){
        if(y==0){
            return true;
        }
//        System.out.println(x-1+" "+(y-1));
        for(int i=y-1;i<=x-1;i++){
            from += combinationTable[y-1][i];

//            System.out.println("from"+from);
            if(k<=from){
//                System.out.println(from);
//                System.out.println("i"+i);
                result.setCharAt(n+m-1-i,'z');
//                System.out.println(result);
                from-=combinationTable[y-1][i];
                return getString(i,y-1,from);
            }
        }
        return false;
    }

}
