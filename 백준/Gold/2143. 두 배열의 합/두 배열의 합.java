import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        long t = Long.parseLong(bf.readLine());
        int n1 = Integer.parseInt(bf.readLine());
        int[] array1 = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n2 = Integer.parseInt(bf.readLine());
        int[] array2 = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Map<Long,Long> mapSubSumToNum1 = createMapSubSumToNum(array1,n1);
        Map<Long,Long> mapSubSumToNum2 = createMapSubSumToNum(array2,n2);

        long result = 0;
        for(long subSum1:mapSubSumToNum1.keySet()){
            if(mapSubSumToNum2.containsKey(t-subSum1)){
                result += mapSubSumToNum1.get(subSum1)*mapSubSumToNum2.get(t-subSum1);
            }
        }
        System.out.println(result);
    }

    public static Map<Long,Long> createMapSubSumToNum(int[] array,int n){
        Map<Long,Long> map = new HashMap<>();

        long[] prefixSum = new long[n];
        prefixSum[0] = array[0];
        map.put(prefixSum[0], 1L);
        for(int i=1;i<n;i++){
            prefixSum[i] += prefixSum[i-1]+array[i];
            if(!map.containsKey(prefixSum[i])){
                map.put(prefixSum[i], 1L);
            }
            else{
                map.replace(prefixSum[i],map.get(prefixSum[i])+1);
            }

        }

        for(int i=1;i<n;i++){
            for(int j=i;j<n;j++){
                long subSum = prefixSum[j]-prefixSum[i-1];
                if(!map.containsKey(subSum)){
                    map.put(subSum,1L);
                }
                else {
                    map.replace(subSum,map.get(subSum)+1);
                }
            }
        }
        return map;
    }
}
