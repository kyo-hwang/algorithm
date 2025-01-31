import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static int n;
    public static int[] snows;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        snows = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(snows);
        int result =Integer.MAX_VALUE;
        for(int start=0;start<n;start++){
            for(int end=start+3;end<n;end++){
                int standard = snows[end]+snows[start];
                int left = start+1;
                int right = end-1;
                while(right>left){
                    int curVal = snows[right]+snows[left];
                    result = Math.min(result,Math.abs(curVal-standard));
                    if(curVal-standard>0){
                        right--;
                    } else if (curVal-standard<0) {
                        left++;
                    }else {
                        break;
                    }
                }
            }
        }

        System.out.println(result);
    }
}
