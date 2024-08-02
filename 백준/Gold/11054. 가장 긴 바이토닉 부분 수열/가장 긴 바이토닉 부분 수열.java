import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] numLine = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] incrementDp = new int[n+1];
        int[] decrementDp = new int[n+1];

        Arrays.fill(incrementDp,1);
        Arrays.fill(decrementDp,1);

        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(numLine[i]>numLine[j]){
                    incrementDp[i] = Math.max(incrementDp[i],incrementDp[j]+1);
                }
            }
        }

        for(int i=n-2;i>0;i--){
            for(int j=i+1;j<n;j++){
                if(numLine[i]>numLine[j]){
                    decrementDp[i] = Math.max(decrementDp[i],decrementDp[j]+1);
                }
            }
        }

        int result = 0;
        for(int i=0;i<n;i++){
            int point = numLine[i];
            int inc = 0;
            int dec = 0;
            for(int j=0;j<i;j++){
                if(numLine[j]<point){
                    inc = Math.max(inc,incrementDp[j]);
                }
            }
            for(int j=i+1;j<n;j++){
                if(numLine[j]<point){
                    dec = Math.max(dec,decrementDp[j]);
                }
            }
            result = Math.max(result,inc+dec+1);
        }

        System.out.println(result);
    }
}
