
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        int[] numLine = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[n];
        Arrays.fill(dp,Integer.MAX_VALUE);

        int maxLoc = -1;
        int[] maxSubLengths = new int[n];
        for(int i=0;i<numLine.length;i++){
            int loc = findLoc(numLine[i],dp);
            if(loc>maxLoc){
                maxLoc = loc;
            }
            dp[loc] = numLine[i];
            maxSubLengths[i] = loc;
        }

        System.out.println(maxLoc+1);

        List<Integer> subNumLine = new ArrayList<>();

        int last = -1;
        for(int i=numLine.length-1;i>=0;i--){
            if(numLine[i]==dp[maxLoc]){
                last = i;
            }
        }

        int cur = maxLoc;
        int lastMax = Integer.MAX_VALUE;
        for(int i=numLine.length-1;i>=0;i--){
            if(numLine[i]<lastMax&&maxSubLengths[i]==cur){
                cur--;
                subNumLine.add(numLine[i]);
            }
        }
        

        for(int i=subNumLine.size()-1;i>=0;i--){
            if(i==0){
                System.out.print(subNumLine.get(i));
                break;
            }
            System.out.print(subNumLine.get(i)+" ");
        }
    }
    public static int findLoc(int num,int[] dp){
        int left = 0;
        int right = dp.length-1;

        while(right>=left) {
            int mid = (left + right) / 2;
            if (dp[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
