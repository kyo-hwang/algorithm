import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static List<int[]> locs;
    public static int n;
    public static double min = Double.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        for(int i=0;i<t;i++){
            min = Double.MAX_VALUE;
//            System.out.println(min);
            n = Integer.parseInt(bf.readLine());
            locs = new ArrayList<>();
            for(int j=0;j<n;j++){
                locs.add(Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
            }
            back(0,0,1,0,new boolean[n]);
            System.out.println(min);
        }
    }
    public static void back(long x,long y,int num,int start,boolean[] checked){
        if(num==n/2+1){
//            System.out.println("x "+x);
//            System.out.println("y "+y);
//            System.out.println(Arrays.toString(checked));
            calLength(x,y,checked);
            return;
        }
        long tmpX = x;
        long tmpY = y;
        for(int i=start;i<n;i++){
            x = tmpX;
            y = tmpY;
            if(num+n-i<n/2){
                return;
            }
            x -= locs.get(i)[0];
            y -= locs.get(i)[1];
//            System.out.println("x "+x+" y "+y);
            checked[i] = true;
            back(x,y,num+1,i+1,checked);
            checked[i] = false;
        }
    }
    public static void calLength(long x, long y,boolean[] checked){
        for(int i=0;i<n;i++){
            if(!checked[i]){
                x+= locs.get(i)[0];
                y+= locs.get(i)[1];
            }
        }
//        System.out.println(x);
//        System.out.println(y);
        min = Math.min(min,Math.sqrt(Math.pow(x,2)+Math.pow(y,2)));
    }
}
