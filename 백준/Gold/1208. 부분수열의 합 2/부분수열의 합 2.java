import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int s = input[1];

        int[] numLine = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] left = Arrays.copyOf(numLine,n/2);
        int[] right = Arrays.copyOfRange(numLine,n/2,n);


        List<Integer> leftAllSubSum = new ArrayList<>();
        List<Integer> rightAllSubSum = new ArrayList<>();
        getAllPossibleSubSum(0,0,left,leftAllSubSum);
        getAllPossibleSubSum(0,0,right,rightAllSubSum);
        rightAllSubSum.sort((e1,e2)->e1-e2);

        long count = 0;

        for(int num:leftAllSubSum){
            int locFirst = binarySearchFirst(rightAllSubSum,s-num);
            if(locFirst>=0&&locFirst<=rightAllSubSum.size()-1&&rightAllSubSum.get(locFirst)==s-num){
                int locSecond = binarySearchSecond(rightAllSubSum,s-num);
                count+=locSecond-locFirst+1;
            }

        }
        if(s==0){
            System.out.println(count-1);
            return;
        }
        System.out.println(count);
    }
    public static int binarySearchFirst(List<Integer> nums,int numToFind){
        int left = 0;
        int right = nums.size()-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(nums.get(mid)<numToFind){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return left;
    }
    public static int binarySearchSecond(List<Integer> nums,int numToFind){
        int left =0;
        int right = nums.size()-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(nums.get(mid)<=numToFind){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return right;
    }
    public static void getAllPossibleSubSum(int depth,int tempSum,int[] numLine,List<Integer> allSubSum){
        if(depth==numLine.length){
            allSubSum.add(tempSum);
            return;
        }
        getAllPossibleSubSum(depth+1,tempSum+numLine[depth],numLine,allSubSum);
        getAllPossibleSubSum(depth+1,tempSum,numLine,allSubSum);
    }
}
