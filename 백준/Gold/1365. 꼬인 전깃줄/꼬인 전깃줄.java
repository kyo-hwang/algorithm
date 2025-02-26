import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] numLine = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> temp = new ArrayList<>();
        for(int num:numLine){
            int loc = findLoc(temp,num);
            if(loc>=temp.size()){
                temp.add(num);
            }else{
                temp.set(loc,num);
            }
        }
        System.out.println(n-temp.size());
    }
    public static int findLoc(List<Integer> temp,int num){
        int left = 0;
        int right = temp.size()-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(temp.get(mid)<num){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return left;
    }
}
