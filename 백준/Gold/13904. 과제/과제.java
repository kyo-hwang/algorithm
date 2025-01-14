import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[][] homework = new int[n][2];
        int maxDueDate = -1;
        for(int i=0;i<n;i++){
            homework[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            maxDueDate = Math.max(maxDueDate,homework[i][0]);
        }

        Arrays.sort(homework,(e1,e2)->{
            if(e1[0]>e2[0]){
                return 1;
            }
            else if(e1[0]==e2[0]){
                if(e1[1]>e2[1]){
                    return 1;
                }
            }
            return -1;
        });


        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(e->-e[1]));

        int scoreSum = 0;
        int curHomework = n-1;
        for(int i=maxDueDate;i>=1;i--){
            if(curHomework>=0){
                while(homework[curHomework][0]>=i){
                    pq.add(homework[curHomework]);
                    curHomework--;
                    if(curHomework<0){
                        break;
                    }
                }
            }
            if(!pq.isEmpty()){
                scoreSum+=pq.poll()[1];
            }
        }

        System.out.println(scoreSum);
    }
}
