import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        int[] nums = new int[n];
        int max = 1;
        for(int i=0;i<n;i++){
            nums[i] = Integer.parseInt(bf.readLine());
            if(nums[i]>max){
                max = nums[i];
            }
        }

        int[] zeroDp = new int[max+1];
        int[] oneDp = new int[max+1];

        zeroDp[0] = 1;
        oneDp[1] = 1;

        for(int i=2;i<max+1;i++){
            zeroDp[i] = zeroDp[i-1]+zeroDp[i-2];
            oneDp[i] = oneDp[i-1]+oneDp[i-2];
        }

//        System.out.println(Arrays.toString(zeroDp));
        for(int i=0;i<n;i++){
            System.out.println(zeroDp[nums[i]]+" "+oneDp[nums[i]]);
        }

    }
}
