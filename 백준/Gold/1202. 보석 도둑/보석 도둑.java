import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static class Jewel implements Comparable{
        public Jewel(int[] attribute){
            weight = attribute[0];
            value = attribute[1];
        }
        private int weight;
        private int value;

        public int getWeight(){
            return weight;
        }

        public int getValue(){
            return value;
        }

        public int compareWeightTo(Jewel jewel){
            return this.weight- jewel.weight;
        }

        public int compareValueTo(Jewel jewel){
            return this.value - jewel.value;
        }

        @Override
        public int compareTo(Object o) {
            return compareValueTo((Jewel) o);
        }
    }

    private static int lastJewel = -1;
    private static int from = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] c = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = c[0];
        int k = c[1];

        List<Jewel> jewels =  new ArrayList<>();
        for(int i=0;i<n;i++){
            jewels.add(new Jewel(
                    Arrays.stream(bf.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray()));
        }
        jewels.sort(Jewel::compareWeightTo);

        List<Integer> bags = new ArrayList<>();
        for(int i=0;i<k;i++){
            bags.add(Integer.parseInt(bf.readLine()));
        }
        bags.sort(Integer::compareTo);

        PriorityQueue<Jewel> queue = new PriorityQueue<>((e1,e2)->-e1.compareTo(e2));

        long total = 0;
        for(int i=0;i<k;i++){
            int weight = bags.get(i);
            findPossibleJewel(jewels,weight);
            for(int j=from;j<lastJewel+1;j++){
                queue.offer(jewels.get(j));
            }
            from = lastJewel+1;
            if(!queue.isEmpty()){
                total += queue.poll().getValue();
            }
        }

        System.out.println(total);
    }
    public static void findPossibleJewel(List<Jewel> jewels,int weight){
        for(int i=from;i<jewels.size();){
            if(weight>= jewels.get(i).getWeight()){
                lastJewel++;
            }
            else{
                return;
            }
            i++;
        }
    }
}
