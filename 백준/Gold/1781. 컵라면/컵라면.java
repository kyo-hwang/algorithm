import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] info = new int[n][2];
        for(int i=0;i<n;i++){
            info[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Arrays.sort(info,(e1,e2)->e2[0]-e1[0]);

//        for(int i=0;i<n;i++){
//            System.out.println(Arrays.toString(info[i]));
//        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((e1,e2)->e2[1]-e1[1]);

        int lastCheck = 0;

        int result = 0;
        for(int i=n;i>=1;i--){
//            System.out.println(lastCheck);
            while(lastCheck<=n-1){
//                System.out.println(info[lastCheck][0]);
                if(info[lastCheck][0]>=i){
                    pq.offer(info[lastCheck]);
                    lastCheck++;
                    continue;
                }
                break;
            }
//            System.out.println("!!");
//            System.out.println(pq);
            if(!pq.isEmpty()){
                result += pq.poll()[1];
//                System.out.println(result);
            }
        }

        System.out.println(result);
    }



}
