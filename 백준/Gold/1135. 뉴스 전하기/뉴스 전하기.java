import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static int n;
    public static int[] parents;
    public static List<List<Integer>> childs = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        parents = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i=0;i<n;i++){
            childs.add(new ArrayList<>());
        }
        for(int i=1;i<n;i++){
            childs.get(parents[i]).add(i);
        }

        System.out.println(calMinTime(0));
    }

    public static int calMinTime(int cur){
        if(childs.get(cur).isEmpty()){
            return 0;
        }

        List<Integer> childTimes = new ArrayList<>();
        for(int child:childs.get(cur)){
            childTimes.add(calMinTime(child));
        }
        childTimes.sort(Comparator.comparingInt(e->-e));
        for(int i=0;i<childTimes.size();i++){
            childTimes.set(i,childTimes.get(i)+1+i);
        }

        int max = childTimes.stream().mapToInt(e->e).max().getAsInt();
        return max;
    }
}
