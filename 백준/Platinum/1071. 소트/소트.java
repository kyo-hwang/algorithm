import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static List<Integer> array;
    public static Deque<Integer> numLine;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        array = Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).sorted().collect(Collectors.toList());
        numLine = new ArrayDeque<>(array);
        Deque<Integer> result = new ArrayDeque<>();
        Deque<Integer> tmp = new ArrayDeque<>();
        int sameNum=0;
        while(!numLine.isEmpty()){
            int cur = numLine.poll();
            if(tmp.isEmpty()){
                tmp.push(cur);
                sameNum=1;
                continue;
            }
            if(sameNum==1){
                if(cur-tmp.peek()==1){
                    tmp.push(cur);
                    sameNum++;
                } else if (cur!=tmp.peek()) {
                    while(!tmp.isEmpty()){
                        result.offer(tmp.pop());
                    }
                    tmp.push(cur);
                    sameNum = 1;
                }
                else {
                    tmp.push(cur);
                }
            }
            else if(sameNum==2){
                if(cur!=tmp.peek()){
                    int first = tmp.peek();
                    while(!tmp.isEmpty()){
                        int num = tmp.pop();
                        if(num==first){
                            numLine.push(num);
                        }
                        else{
                            result.offer(num);
                        }
                    }
                    result.offer(cur);
                    sameNum=0;
                }
                else{
                    tmp.push(cur);
                }
            }
        }
        while(!tmp.isEmpty()){
            result.offer(tmp.pop());
        }
        while (!result.isEmpty()){
            System.out.print(result.pop()+" ");
        }
    }

}
