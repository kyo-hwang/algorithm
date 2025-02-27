import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static int n;
    public static int d;
    public static int k;
    public static int c;

    public static int[] rotation;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        d = input[1];
        k = input[2];
        c = input[3];

        rotation = new int[n];
        for(int i=0;i<n;i++){
            rotation[i] = Integer.parseInt(bf.readLine());
        }

        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<k;i++){
            push(rotation[i],map);
        }

        int result = calNum(map);

        for(int i=k;i<n+k-1;i++){
            push(rotation[i%n],map);
            remove(rotation[i-k],map);
            result = Math.max(calNum(map),result);
        }

        System.out.println(result);
    }

    public static int calNum(Map<Integer,Integer> map){
        if(map.containsKey(c)){
            return map.size();
        }else{
            return map.size()+1;
        }
    }


    public static void push(int e,Map<Integer,Integer> map){
        if(map.containsKey(e)){
            map.replace(e,map.get(e)+1);
        }else{
            map.put(e,1);
        }
    }
    public static void remove(int e,Map<Integer,Integer> map){
        if(map.get(e)==1){
            map.remove(e);
        }else{
            map.replace(e,map.get(e)-1);
        }
    }

}
