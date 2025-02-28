import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int k = input[1];
        int[] numLine = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        Deque<Integer> stack = new ArrayDeque<>();
        int removedCount = 0;
        int i;
        for(i=0;i<numLine.length;i++){
            while(!stack.isEmpty()){
                if(removedCount==k){
                    break;
                }
                if(numLine[i]>stack.peekFirst()){
                    stack.pop();
                    removedCount++;
                }else{
                    break;
                }
            }
            stack.push(numLine[i]);
        }

        for(int j=removedCount+1;j<=k;j++){
            stack.pop();
        }

        while(!stack.isEmpty()){
            System.out.print(stack.pollLast());
        }
    }
}
