import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main{
    public static int row;
    public static int col;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bf.readLine());

        for(int i=0;i<t;i++){
            int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            row = input[0];
            col = input[1];
            String[][] seats = new String[row][col];
            for(int j=0;j<row;j++){
                seats[j] = bf.readLine().split("");
            }
            System.out.println(run(seats));
        }

    }
    public static int run(String[][] seats) {
        int init = 0;
        Set<Integer> beforePossibles = new HashSet<>();
        beforePossibles.add(init);
        int[] beforeDp = new int[1<<col];
        for(int i=0;i<row;i++){
            Set<Integer> afterPossibles = new HashSet<>();
            int[] afterDp = new int[1<<col];
            findPossible(seats[i],beforePossibles,afterPossibles,beforeDp,afterDp);
            beforePossibles = afterPossibles;
            beforeDp =afterDp;
//            System.out.println(Arrays.toString(beforeDp));
        }

        int max = 0;
        for(int i=0;i<1<<col;i++){
            max = Math.max(beforeDp[i],max);
        }
        return max;
    }

    public static void findPossible(String[] rowSeat,Set<Integer> beforePossibles,Set<Integer> afterPossibles, int[] beforeDp,int[] afterDp){
        int max = 0;
        for(int b: beforeDp){
            max = Math.max(b,max);
        }
        afterPossibles.add(0);
        afterDp[0] = max;
//        System.out.println("beforePossible"+beforePossibles);
//        System.out.println("beforeDp : "+Arrays.toString(beforeDp));
        for(int up:beforePossibles){
            //아예 인원을 안 두는 경우도 고려해야됨.

//            afterPossibles = new HashSet<>();
            back(rowSeat,0,0,up,afterPossibles,beforeDp,afterDp,1);
        }
    }
    public static void back(String[] rowSeat,int cur,int from,int up,Set<Integer> afterPossible,int[] beforeDp,int[] afterDp,int depth){
        for(int i=from;i<col;i++){
            if(rowSeat[i].equals("x")){
                continue;
            }
            if(i!=col-1){
                if((up&(1<<(i+1)))!=0){
                    continue;
                }
            }
            if(i!=0){
                if((up&(1<<(i-1)))!=0){
                    continue;
                }
            }
            int next = cur|(1<<i);
            afterPossible.add(next);
            afterDp[next] = Math.max(beforeDp[up]+depth,afterDp[next]);

            back(rowSeat,next,i+2,up,afterPossible,beforeDp,afterDp,depth+1);
        }
    }

}
