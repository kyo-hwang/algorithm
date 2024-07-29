import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static class Number {
        public Number(int weight){
            this.weight = weight;
            maxNum = 1;
            maxPoint = -1;
        }
        private int weight;
        private int maxNum;
        private int maxPoint;

        public void setMaxNum(int maxNum){
            this.maxNum = maxNum;
        }

        public void setMaxPoint(int maxPoint){
            this.maxPoint = maxPoint;
        }

        public int getMaxNum(){
            return maxNum;
        }

        public int getMaxPoint(){
            return maxPoint;
        }

        public int getWeight(){
            return weight;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int[] numLine = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Number> numbers = Arrays.stream(numLine)
                .mapToObj(Number::new)
                .collect(Collectors.toList());

        for(int i=1;i<n;i++){
            for(int j=i;j>=0;j--){
                if(numbers.get(i).getWeight()>numbers.get(j).getWeight()){
                    if(numbers.get(i).getMaxNum()<(numbers.get(j).getMaxNum()+1)){
                        numbers.get(i).setMaxNum(numbers.get(j).getMaxNum()+1);
                        numbers.get(i).setMaxPoint(j);
                    }
                }
            }
        }

        int maxNum = 0;
        for(int i=1;i<n;i++){
            if(numbers.get(i).getMaxNum()>numbers.get(maxNum).getMaxNum()){
                maxNum = i;
            }
        }
        Deque<Integer> subLine = new ArrayDeque<>();
        int next = maxNum;
        while(next!=-1){
            subLine.push(numbers.get(next).getWeight());
            next = numbers.get(next).getMaxPoint();
        }
        System.out.println(numbers.get(maxNum).getMaxNum());
        while(!subLine.isEmpty()){
            System.out.print(subLine.pop()+" ");
        }
        System.out.println();
    }
}
