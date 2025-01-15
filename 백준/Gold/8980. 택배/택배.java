import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int villageCount = input[0];
        int truckVolume = input[1];
        int n = Integer.parseInt(bf.readLine());

        int[][] postInfo = new int[n][3];
        for(int i=0;i<n;i++){
            postInfo[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int[] remain = new int[villageCount+1];
        Arrays.fill(remain,truckVolume);



        Arrays.sort(postInfo,(e1,e2)->{
            if(e1[1]>e2[1]){
                return 1;
            }else if(e1[1]==e2[1]){
                if(e1[0]<e2[0]){
                    return 1;
                }
            }
            return -1;
        });

//        for(int i=0;i<n;i++){
//            System.out.println(Arrays.toString(postInfo[i]));
//        }

        int result = 0;
        for(int i=0;i<n;){
            int curDestination = postInfo[i][1];
            int curVolume = 0;
            for(int j=i;j<n;j++){
                if(postInfo[j][1]!=curDestination){
                    break;
                }
                int addVolume = Math.min(postInfo[i][2],remain[postInfo[i][0]]);
                addVolume = Math.min(truckVolume-curVolume,addVolume);
                curVolume+=addVolume;
                i++;
            }
            for(int j=curDestination-1;j>=0;j--){
                remain[j] = Math.max(0,remain[j]-curVolume);
            }
            result+=curVolume;
        }

        System.out.println(result);
    }
}
