import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        long[] nums = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long start = nums[0];
        long end = nums[1];

        long length = end-start+1;

        boolean[] isNum = new boolean[(int)length];

        for(int i=2;i<=Math.sqrt(end);i++){
            long s = (long) i *(long)i;
            for(long j=start+s-(start-1)%s-1;j<=end;j+=s){
                if(j%s==0){
                    int e = (int)(j-start);
                    isNum[e] = true;
                }
            }
        }

        int result = 0;
        for(boolean is:isNum){
            if(!is){
                result++;
            }
        }

        System.out.println(result);

    }
}
