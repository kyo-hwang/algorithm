import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());

        List<Integer> inter = new ArrayList<>();
        int last = 0;
        for(int i= 0;i<m;i++){
            int vip = Integer.parseInt(bf.readLine());
            vip--;
            if(vip-last!=0){
                inter.add(vip-last);
            }
            last = vip+1;
        }
        if(n-last!=0){
            inter.add(n-last);
        }

        List<Integer> dp = new ArrayList<>();

        dp.add(0);
        dp.add(1);
        dp.add(2);
        
        for(int i=3;i<n+1;i++){
            dp.add(dp.get(i-1)+dp.get(i-2));
        }

        int result = 1;
        for (Integer in : inter){
            result*=dp.get(in);
        }

        System.out.println(result);
    }
}
