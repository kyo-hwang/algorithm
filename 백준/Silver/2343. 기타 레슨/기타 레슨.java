import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static int getCdSizePossible(List<Integer> courseLength, int cdLength){
        int leftLength = cdLength;
        int cdSizePossible = 1;
        for(int i=0;i<courseLength.size();i++){
            if(courseLength.get(i)>leftLength){
                leftLength = cdLength;
                cdSizePossible++;
            }
            leftLength -= courseLength.get(i);
        }
        return cdSizePossible;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] command = bf.readLine().split(" ");
        int courseN = Integer.parseInt(command[0]);
        int cdN = Integer.parseInt(command[1]);

        List<Integer> courseLength = Arrays.stream(bf.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        int left = courseLength.stream().max(Integer::compareTo).get();
        int right = courseLength.stream().mapToInt(Integer::intValue).sum();
        while(left<=right){
            int mid = (left+right)/2;
            int cdNPossible = getCdSizePossible(courseLength,mid);
            if(cdNPossible<=cdN){
                right = mid-1;
            }
            else{
                left = mid+1;
            }
        }
        System.out.println(left);
    }
}
