import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Integer t = Integer.parseInt(bf.readLine());
        for(int i=0;i<t;i++){
            int n = Integer.parseInt(bf.readLine());
            int[] numLine = new int[n];
            for(int j=0;j<=n/10;j++){
                int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                System.arraycopy(input,0,numLine,j*10,input.length);
            }
            StringBuffer r = calMid(numLine);
            System.out.println(r);
        }
    }
    public static StringBuffer calMid(int[] numLine){
        StringBuffer result = new StringBuffer(1+(numLine.length-1)/2+"\n");
        PriorityQueue<Integer> smalls = new PriorityQueue<>(Comparator.comparingInt(e->-e));
        PriorityQueue<Integer> bigs = new PriorityQueue<>(Comparator.comparingInt(e->e));
        for(int i=0;i<numLine.length;i++){
            if(i%2==0){
                smalls.offer(numLine[i]);
                bigs.offer(smalls.poll());
                result.append(bigs.peek()+" ");
            }else{
                if(bigs.peek()<=numLine[i]){
                    bigs.offer(numLine[i]);
                    smalls.offer(bigs.poll());
                }else{
                    smalls.offer(numLine[i]);
                }
            }
        }
        return result;
    }
}
