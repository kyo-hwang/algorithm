import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static List<Integer> inputIntegerArray (BufferedReader bf) throws Exception{
        return Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> commands = inputIntegerArray(bf);
        int N = commands.get(0);
        int L = commands.get(1);

        List<List<Integer>> ponds = new ArrayList<>();
        for(int i=0;i<N;i++){
            ponds.add(inputIntegerArray(bf));
        }

        ponds.sort((e1,e2)->e1.get(0)-e2.get(0));
        int result = 0;
        int last = -1;
        for(List<Integer> pond:ponds){
            if(pond.get(0)>=last){
                int LN = ((pond.get(1)-pond.get(0))+L-1)/L;
                last = pond.get(0)+LN*L;
                result += LN;
            }
            else{
                if(pond.get(1)<=last){
                    continue;
                }
                int LN = ((pond.get(1)-last)+L-1)/L;
                last = last+LN*L;
                result+=LN;
            }
        }
        System.out.println(result);
    }
}
