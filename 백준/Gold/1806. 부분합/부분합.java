import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        long[] input = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long n = input[0];
        long sum = input[1];

        long[] numLine = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        int result = Integer.MAX_VALUE;

        long curSum = numLine[0];
        int left=0;
        int right=0;
        while(right<n){
            if(curSum<sum){
                right++;
                if(right>=n){
                    break;
                }
                curSum+=numLine[right];
            }else{
                result = Math.min(result,right-left+1);
                
                curSum-=numLine[left];
                left++;
                if(left>right){
                    right=left;
                    if(right>=n){
                        break;
                    }
                    curSum = numLine[left];
                }
            }
        }

        if(result==Integer.MAX_VALUE){
            System.out.println(0);
        }else{
            System.out.println(result);
        }
    }
}
