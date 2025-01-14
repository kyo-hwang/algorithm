import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static int[] monthLastDay = new int[13];
    public static final int[] dayOfMonth = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        for(int i=2;i<=12;i++){
            monthLastDay[i] += monthLastDay[i-1]+dayOfMonth[i-1];
        }


        int[][] flowers = new int[n][2];
        for(int i=0;i<n;i++){
            int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            flowers[i][0] = monthLastDay[input[0]]+input[1];
            flowers[i][1] = monthLastDay[input[2]]+input[3];
        }

        
        int result = 0;
        int start = monthLastDay[3]+1;
        int last = monthLastDay[11]+30;


        Arrays.sort(flowers, Comparator.comparingInt(e->e[0]));

        for(int i=0;i<n;){
            if(flowers[i][0]>start){
                break;
            }
            int maxLast=-1;
            for(int j=i;j<n;j++){
                if(flowers[j][0]<=start){
                    maxLast = Math.max(maxLast,flowers[j][1]);
                    i++;
                }
                else{
                    break;
                }
            }
            //이러면 만들 수 없다.
            if(maxLast<=start){
                break;
            }
            start=maxLast;
            result++;
            if(maxLast>last){
                break;
            }
        }

        if(start>last){
            System.out.println(result);
        }else {
            System.out.println(0);
        }
    }
}
