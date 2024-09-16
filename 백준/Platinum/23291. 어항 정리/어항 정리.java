import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main{
    static int[] dRow = {0,1,0,-1};
    static int[] dCol = {1,0,-1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int k = input[1];
        List<Integer> fishBowl = new ArrayList<>(Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
        int result = 0;
        int interval = getMaxInterval(fishBowl);
//        int min = getMin(fishBowl);
//        incrementValueWith(min,fishBowl);
//        System.out.println(fishBowl);
//        fishBowl = rotate1(fishBowl);
//        System.out.println(fishBowl);
        while(interval>k){
            int min = getMin(fishBowl);
            incrementValueWith(min,fishBowl);
            fishBowl = rotate1(fishBowl);
//            System.out.println(fishBowl);
            fishBowl = rotate2(fishBowl);
            interval = getMaxInterval(fishBowl);
            result++;
        }
        System.out.println(result);
    }

    public static int getMin(List<Integer> fishBowl){
        int min =Integer.MAX_VALUE;
        for(int i=0;i<fishBowl.size();i++){
            if(fishBowl.get(i)<min){
                min = fishBowl.get(i);
            }
        }
        return min;
    }
    public static int getMaxInterval(List<Integer> fishBowl){
        int min = Integer.MAX_VALUE;
        int max = -1;

        for(int i=0;i<fishBowl.size();i++){
            if(fishBowl.get(i)>max){
                max = fishBowl.get(i);
            }
            if(fishBowl.get(i)<min){
                min = fishBowl.get(i);
            }
        }
        return max-min;
    }

    public static void incrementValueWith(int value,List<Integer> fishBowl){
        for(int i=0;i<fishBowl.size();i++){
            if(fishBowl.get(i)==value){
                fishBowl.set(i,fishBowl.get(i)+1);
            }
        }
    }

    public static List<Integer> rotate1(List<Integer> fishBowl){
        List<List<Integer>> tmp = new ArrayList<>();
        tmp.add(new ArrayList<>());
        tmp.add(new ArrayList<>());
        tmp.get(1).add(fishBowl.get(0));
        for(int i=1;i<fishBowl.size();i++){
            tmp.get(0).add(fishBowl.get(i));
        }

        int length1 = 1;
        int length2 = 2;

        while(true){

            if(tmp.get(0).size()-length1<length2){
                break;
            }
            List<List<Integer>> tmp2 = new ArrayList<>();
            tmp2.add(new ArrayList<>());
            for(int i=length1;i<tmp.get(0).size();i++){
                tmp2.get(0).add(tmp.get(0).get(i));
            }
//            System.out.println(tmp2);

            for(int i=length1-1;i>=0;i--){
                tmp2.add(new ArrayList<>());
                for(int j=0;j<length2;j++){
                    tmp2.get(length1-i).add(tmp.get(j).get(i));
                }
            }
            int lengthTmp = length2;
            length2 = length1+1;
            length1 = lengthTmp;
            tmp = tmp2;
        }
        tmp = balance(tmp);


        List<Integer> result = new ArrayList<>();
//        System.out.println(length1);
//        System.out.println(length2);
        //다시 일자 어항으로 만든다.
        for(int i=0;i<length1;i++){
            for(int j=0;j<length2;j++){
                result.add(tmp.get(j).get(i));
            }
        }
        for(int i=length1;i<tmp.get(0).size();i++){
            result.add(tmp.get(0).get(i));
        }

        return result;
    }

    public static List<Integer> rotate2(List<Integer> fishBowl){
//        System.out.println(fishBowl);
        int n = fishBowl.size();
        List<List<Integer>> tmp = new ArrayList<>();
        tmp.add(new ArrayList<>());
        for(int i=n/2;i<n;i++){
            tmp.get(0).add(fishBowl.get(i));
        }
        tmp.add(new ArrayList<>());
        for(int i=n/2-1;i>=0;i--){
            tmp.get(1).add(fishBowl.get(i));
        }
//        System.out.println("2tmp"+tmp);
        List<List<Integer>> tmp2 = new ArrayList<>();
        tmp2.add(new ArrayList<>());
        tmp2.add(new ArrayList<>());
        for(int i=0;i<2;i++){
            for(int j=n/4;j<n/2;j++){
                tmp2.get(i).add(tmp.get(i).get(j));
            }
        }
        tmp2.add(new ArrayList<>());
        tmp2.add(new ArrayList<>());
        for(int i=2;i<4;i++){
            for(int j=n/4-1;j>=0;j--){
                tmp2.get(i).add(tmp.get(3-i).get(j));
            }
        }
//        System.out.println("2tmp2"+tmp2);

        List<List<Integer>> tmpBalanced = balance(tmp2);

        //밸런싱한 어항을 다시 일자로
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<tmpBalanced.get(0).size();i++){
            for (List<Integer> integers : tmpBalanced) {
                result.add(integers.get(i));
            }
        }
        return result;
    }

    //새로 밸런싱한 새로운 리스트를 반환한다.
    public static List<List<Integer>> balance(List<List<Integer>> fishBowl){
        List<List<Integer>> newFishBowl = new ArrayList<>();
        for(int i=0;i<fishBowl.size();i++){
            newFishBowl.add(new ArrayList<>(fishBowl.get(i)));
        }
        for(int i=0;i<fishBowl.size();i++){
            for(int j=0;j< fishBowl.get(i).size();j++){
                for(int d=0;d<4;d++){
                    int nRow = i+dRow[d];
                    int nCol = j+dCol[d];
                    if(nRow>=0&&nRow<fishBowl.size()&&nCol<fishBowl.get(nRow).size()&&nCol>=0){
                        int curVal = fishBowl.get(i).get(j);
                        int nearVal = fishBowl.get(nRow).get(nCol);
                        if(curVal>nearVal){
                            newFishBowl.get(i).set(j,newFishBowl.get(i).get(j)-(curVal-nearVal)/5);
                        }
                        if(nearVal>curVal){
                            newFishBowl.get(i).set(j,newFishBowl.get(i).get(j)+(nearVal-curVal)/5);
                        }
                    }
                }
            }
        }
        return newFishBowl;
    }
}
