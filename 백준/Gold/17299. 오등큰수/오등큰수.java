import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] numLine = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int maxValue = Arrays.stream(numLine).max().getAsInt();
        int[] numCount = new int[maxValue+1];
        for(int i=0;i<n;i++){
            numCount[numLine[i]]++;
        }

        int[] numHeight = new int[n];
        for(int i=0;i<n;i++){
            numHeight[i] = numCount[numLine[i]];
        }

        Deque<List<Integer>> stack = new ArrayDeque<>();
        int[] result = new int[n];
        for(int i=n-1;i>=0;i--){
            while(!stack.isEmpty()){
                if(numHeight[i]<stack.getFirst().get(1)){
                    break;
                }else{
                    stack.pop();
                }
            }
            if(stack.isEmpty()){
                result[i] = -1;
            }else{
                result[i] = stack.getFirst().get(0);
            }
            stack.push(List.of(numLine[i],numHeight[i]));
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0;i<n;i++){
            bw.write(result[i]+" ");
        }
        bw.flush();
        bw.close();
    }
}
