import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> a = Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int k = a.get(1);
        int l = a.get(2);
        int n = 0;
        while(k!=l){
            n++;
            k= (k+1)/2;
            l= (l+1)/2;
        }
        System.out.println(n);
    }
}
