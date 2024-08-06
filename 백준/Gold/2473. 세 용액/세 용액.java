import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());

        long[] solutions = Arrays.stream(bf.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        Arrays.sort(solutions);

        long[] result = new long[3];
        long distanceFromZero = Long.MAX_VALUE;
        for(int i=0;i<n-2;i++){
            int j=i+1;
            int k=n-1;
            while(j<k){
                long sum = solutions[i]+solutions[j]+solutions[k];
                if(Math.abs(sum)<distanceFromZero){
                    result = new long[]{solutions[i],solutions[j],solutions[k]};
                    distanceFromZero = Math.abs(sum);
                }
                if(sum<=0){
                    j++;
                }
                else{
                    k--;
                }
            }
        }
        System.out.println(result[0]+" "+result[1]+" "+result[2]);
    }
}
