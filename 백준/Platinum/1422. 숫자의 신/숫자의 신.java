import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = input[0];
        int n = input[1];
        String[] nums = new String[n];
        String max = "0";

        for(int i=0;i<k;i++){
            nums[i] = bf.readLine();
            if(compareNum(nums[i],max)>0){
                max = nums[i];
            }
        }


        for(int i=k;i<n;i++){
            nums[i] = max;
        }

        Arrays.sort(nums,(e1,e2)->{
            String e1First = e1+e2;
            String e2First = e2+e1;
            return -e1First.compareTo(e2First);
        });

        for(int i=0;i<n;i++){
            System.out.print(nums[i]);
        }
    }

    public static int compareNum(String num1,String num2){
        if(num1.length()>num2.length()){
            return 1;
        }else if(num2.length()>num1.length()){
            return -1;
        }
        else{
            return num1.compareTo(num2);
        }
    }

}
