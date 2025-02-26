import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static int s;
    public static int l;
    public static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        s = input[0];
        l = input[1];

        map = new int[s][s];
        for(int i=0;i<s;i++){
            map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int result = 0;
        for(int i=0;i<s;i++){
            if(canReach(map[i])){
                result++;
            }
            int[] tempRoad = new int[s];
            for(int j=0;j<s;j++){
                tempRoad[j] = map[j][i];
            }
            if(canReach(tempRoad)){
                result++;
            }
        }
        System.out.println(result);
    }

    public static boolean canReach(int[] road){
        boolean[] isTemp= new boolean[road.length];
        for(int i=1;i<road.length;i++){
            if(road[i]==road[i-1]){
                continue;
            }
            if(road[i]==road[i-1]-1){
                isTemp[i] = true;
                int j=0;
                for(j=i+1;j<i+l;j++){
                    if(j>=road.length){
                        return false;
                    }
                    if(road[i]!=road[j]){
                        return false;
                    }
                    isTemp[j] = true;
                }
                i=j-1;
                continue;
            }
            if(road[i]==road[i-1]+1){
                for(int j=i-1;j>=i-l;j--){
                    if(j<0){
                        return false;
                    }
                    if(road[i-1]!=road[j]){
                        return false;
                    }
                    if(isTemp[j]){
                        return false;
                    }
                }
                continue;
            }
            return false;
        }


        return true;
    }
}
