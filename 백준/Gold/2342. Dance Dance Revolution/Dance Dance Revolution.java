import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] numLine = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        if(numLine.length==1){
            System.out.println(0);
            return;
        }

        int[][] dp = new int[5][5];
        initDp(dp);
        dp[0][numLine[0]] = calCost(0,numLine[0]);

        for(int i=1;numLine[i]!=0;i++){
            int[][] newDp = new int[5][5];
            initDp(newDp);
            for(int j=0;j<5;j++){
                for(int k=0;k<5;k++){
                    if(dp[j][k]==Integer.MAX_VALUE){
                        continue;
                    }
                    if(j==numLine[i]||k==numLine[i]){
                        newDp[j][k] = Math.min(newDp[j][k],dp[j][k]+1);
                    }else{
                        newDp[j][numLine[i]] = Math.min(newDp[j][numLine[i]],dp[j][k]+calCost(k,numLine[i]));
                        newDp[numLine[i]][k] = Math.min(newDp[numLine[i]][k],dp[j][k]+calCost(j,numLine[i]));
                    }
                }
            }
            dp = newDp;
        }
        int result = Integer.MAX_VALUE;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                result = Math.min(result,dp[i][j]);
            }
        }
        System.out.println(result);
    }

    public static void initDp(int[][] dp){
        for(int i=0;i<5;i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
    }

    public static void putMap(Map<Set<Integer>,Integer> dp,Set<Integer> key,int value){
        if(!dp.containsKey(key)){
            dp.put(key,value);
        }else{
            dp.replace(key,Math.min(dp.get(key),value));
        }
    }

    public static int calCost(int start,int end){
        if(start==0){
            return 2;
        }
        int interval = Math.abs(start-end);
        if(interval==2){
            return 4;
        }else if(interval==0){
            return 1;
        }
        return 3;
    }
}
