import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

        int[] numLine = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Deque<Integer> stack = new ArrayDeque<>();
        int[] NGE = new int[n];
        NGE[n-1] = -1;
        stack.push(numLine[n-1]);

        for(int i=n-2;i>=0;i--){
            while((!stack.isEmpty())&&numLine[i]>=stack.getFirst()){
                stack.pop();
            }
            if(stack.isEmpty()){
                NGE[i] = -1;
            }
            else{
                NGE[i] = stack.getFirst();
            }
            stack.push(numLine[i]);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=0;i<n;i++){
            bw.write(Integer.toString(NGE[i])+" ");
        }
        bw.flush();
        bw.close();
    }
}
