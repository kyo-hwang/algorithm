import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static int k;
    public static int n;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        k = Integer.parseInt(bf.readLine());
        int left = 1;
        int right = Math.min((int)Math.pow(n,2),1000000000);

        while(left<=right){
            int mid = (left+right)/2;
            int count  = getCount(mid);
            if(count<k){
                left =mid+1;
            } else {
                right = mid-1;
            }
        }
        System.out.println(left);
    }
    public static int getCount(int num){
        int count=0;
        for(int i=1;i<=n;i++){
            count+=Math.min(num/i,n);
        }
        return count;
    }
}
