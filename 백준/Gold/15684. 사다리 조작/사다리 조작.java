import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static int rs;
    public static int cs;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cs = input[0];
        rs = input[2];
        int n = input[1];
        boolean[][] ladder = new boolean[rs][cs-1];
        for(int i=0;i<n;i++){
            input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ladder[input[0]-1][input[1]-1] = true;
        }
        if(check(ladder)){
            System.out.println(0);
            return;
        }
        int result = back(0,-1,1,ladder);
        if(result==Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        System.out.println(result);

    }
    public static int back(int lastRow,int lastCol,int depth,boolean[][] ladder){
        if(depth>=4){
            return Integer.MAX_VALUE;
        }
        int result = Integer.MAX_VALUE;
        for(int i=lastCol+1;i<cs-1;i++){
            if(!ladder[lastRow][i]){
                if(i>=1&&ladder[lastRow][i-1]){
                    continue;
                }
                if(i<cs-2&&ladder[lastRow][i+1]){
                    continue;
                }
                ladder[lastRow][i] = true;
                if(check(ladder)){
                    ladder[lastRow][i] = false;
                    return depth;
                }
                result = Math.min(back(lastRow,i+1,depth+1,ladder),result);
                ladder[lastRow][i] = false;
            }
        }

        for(int i=lastRow+1;i<rs;i++){
            for(int j=0;j<cs-1;j++){
                if(!ladder[i][j]){
                    if(j>=1&&ladder[i][j-1]){
                        continue;
                    }
                    if(j<cs-2&&ladder[i][j+1]){
                        continue;
                    }
                    ladder[i][j] = true;
                    if(check(ladder)){
                        ladder[i][j] = false;
                        return depth;
                    }
                    result = Math.min(back(i,j+1,depth+1,ladder),result);
                    ladder[i][j] = false;
                }
            }
        }
        return result;
    }

    public static boolean check(boolean[][] ladder){
        for(int i=0;i<cs-1;i++){
            int loc = i;
            for(int j=0;j<rs;j++){
                if(loc<cs-1&&ladder[j][loc]){
                   loc++;
                   continue;
                }
                if(loc>=1&&ladder[j][loc-1]){
                    loc--;
                }
            }
            if(loc!=i){
                return false;
            }
        }
        return true;
    }

}
