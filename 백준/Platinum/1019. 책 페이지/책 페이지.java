import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static long[] numExistCount = new long[10];
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String numString = bf.readLine().strip();

        int length = numString.length();
        getTotal(numString.length()-1);

        for(int i=0;i<=length-1;i++){
            if(i!=0&&Character.getNumericValue(numString.charAt(i))!=0){
                numExistCount[0] += (long)Math.pow(10,(length-i-1));
                for(int k=0;k<=9;k++){
                    numExistCount[k] += (long)Math.pow(10,length-i-1)*(length-i-1)/10;
                }
            }
            for(int j=1;j<=Character.getNumericValue(numString.charAt(i))-1;j++){
                numExistCount[j] += (long)Math.pow(10,(length-i-1));
                for(int k=0;k<=9;k++){
                    numExistCount[k] += (long)Math.pow(10,length-i-1)*(length-i-1)/10;
                }
            }

            if(i==length-1){
                numExistCount[Character.getNumericValue(numString.charAt(i))] +=1;
                continue;
            }
            numExistCount[Character.getNumericValue(numString.charAt(i))] += (Integer.parseInt(numString.substring(i+1))+1);
        }

        for(int i=0;i<10;i++){
            System.out.print(numExistCount[i]+" ");
        }

    }


    public static void getTotal(int size){
        if(size <=0){
            return;
        }
        for(int i=1;i<=size;i++){
            long noneZeroPlus = (long)Math.pow(10,i-1);
            long allPlus = 9*(long)Math.pow(10,i-1)*(i-1)/10;
            numExistCount[0] += allPlus;
            for(int j=1;j<10;j++){
                numExistCount[j] += (allPlus+noneZeroPlus);
            }
        }
    }
}
