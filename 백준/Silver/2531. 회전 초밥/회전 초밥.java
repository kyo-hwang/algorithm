import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = input[0];
        int sushiCategory = input[1];
        int continuityNum = input[2];
        int couponNum = input[3];

        List<Integer> sushies = new ArrayList<>();

        for(int i=0;i<n;i++){
            sushies.add(Integer.parseInt(bf.readLine()));
        }

        int[] currShushies = new int[sushiCategory+1];

        currShushies[couponNum] = 1;
        int result = 1;
        int maxResult = 1;

        for(int i=0;i<continuityNum;i++){
            if(currShushies[sushies.get(i)] == 0){
                result++;
            }
            currShushies[sushies.get(i)] +=1;
        }
        maxResult = Math.max(maxResult,result);

        for(int i=continuityNum;i<(continuityNum+n-1);i++){
            if(currShushies[sushies.get(i%n)] == 0){
                result++;
            }
            currShushies[sushies.get(i%n)] +=1;

            currShushies[sushies.get(i-continuityNum)] -=1;
            if(currShushies[sushies.get(i-continuityNum)]==0){
                result-=1;
            }

            maxResult = Math.max(result,maxResult);
        }

        System.out.println(maxResult);
    }
}
