import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numLine  = Arrays.stream(bf.readLine().split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int oneNum = numLine.stream().reduce(0,(e1,e2)->{
            if(e2==1){
                return e1+1;
            }
            return e1;
        });

        int zeroNum = numLine.size()-oneNum;

        int removeOneNum = oneNum/2;
        for(Iterator<Integer> it = numLine.iterator();it.hasNext();){
            if(removeOneNum==0){
                break;
            }
            Integer num = it.next();
            if(num == 1){
                it.remove();
                removeOneNum--;
            }
        }

        int removeZeroNum = zeroNum/2;
        ListIterator<Integer> it = numLine.listIterator(numLine.size());
        while(it.hasPrevious()){
            if(removeZeroNum==0){
                break;
            }
            Integer num = it.previous();
            if(num==0){
                it.remove();
                removeZeroNum--;
            }
        }

        for(int num:numLine){
            System.out.print(num);
        }
    }
}
