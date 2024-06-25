import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> a = Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int rN = a.get(0);
        int k = a.get(1);
        int l = a.get(2);
        int n = 0;
        while(k!=l){
            if(k%2==1){
                k = k/2+1;
            }
            else{
                k = k/2;
            }
            if(l%2==1){
                l = l/2+1;
            }
            else{
                l = l/2;
            }
            n++;
        }
        System.out.println(n);
    }
}
