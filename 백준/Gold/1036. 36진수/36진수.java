import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class NumBigger implements Comparable<NumBigger>{
        int num;
        int[] bigger;
        public NumBigger(int num){
            this.num = num;
            bigger = new int[100];
        }
        public NumBigger(char numChar){
            int tmp = (int)numChar;
            if(tmp<=57){
                num = tmp-48;
            }
            else{
                num = tmp-55;
            }
            bigger = new int[100];
        }

        public void increment(int value,int place){
            int sum = bigger[place]+value;
            if(sum<36){
                bigger[place] = sum;
                return;
            }
            bigger[place] = sum-36;
            increment(1,place+1);
        }

        public String toString(){
            return "(num "+ num+" bigger "+ Arrays.toString(bigger) +")";
        }

        @Override
        public int compareTo(NumBigger c) {
            for(int i=99;i>=0;i--){
                if(this.bigger[i]>c.bigger[i]){
                    return -1;
                }
                else if(this.bigger[i]<c.bigger[i]){
                    return 1;
                }
            }
            return 0;
        }
    }
    public static void main(String[] args) throws Exception {
//        char a = '0';
//        System.out.println((int)'Z');
//        0~9는 48~57 A~Z는 65~90
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        String[] nums = new String[n];
        for(int i=0;i<n;i++){
            nums[i] = bf.readLine().trim();
        }
        int k = Integer.parseInt(bf.readLine());

        HashMap<Integer,NumBigger> map = new HashMap<>();
        for(int i=0;i<=35;i++){
            map.put(i,new NumBigger(i));
        }
//        System.out.println(map);

        for(String num:nums){
            int length = num.length();
//            System.out.println(num+"!!!!!!!!!!!!");
            for(int i=0;i<length;i++){
//                int numToFind = charToNum(num.charAt(i));
//                System.out.println(num.charAt(i));
//                System.out.println(charToNum(num.charAt(i)));
                map.get(charToNum(num.charAt(i))).increment(charMinus('Z',num.charAt(i)),length-i-1);
            }
        }

        List<NumBigger> biggers = new ArrayList<>(map.values());
        Collections.sort(biggers);

        StringBuffer[] numBuffers = new StringBuffer[n];
        for(int i=0;i<n;i++){
            numBuffers[i] = new StringBuffer(nums[i]);
        }

        for(int i=0;i<k;i++){
            for(StringBuffer numBuffer:numBuffers){
                charToZ(numBuffer,numToChar(biggers.get(i).num));
            }
        }

//        System.out.println(Arrays.toString(numBuffers));
        int[][] numsInt = new int[n][100];
        for(int i=0;i<n;i++){
            numsInt[i] = stringToNum(numBuffers[i]);
//            System.out.println(Arrays.toString(numsInt[i]));
        }

        int[] result = numsInt[0];
        for(int i=1;i<n;i++){
            result = plus(result,numsInt[i]);
        }
//        System.out.println(Arrays.toString(result));

        int last = 0;
        for(int i=99;i>=0;i--){
            if(result[i]!=0){
                last = i;
                break;
            }
        }

        StringBuffer real = new StringBuffer();
        for (int i=last;i>=0;i--){
            real.append(numToChar(result[i]));
        }
        System.out.println(real);
    }
    public static int[] plus(int[] a,int[] b){
        int[] result = new int[100];
        int up = 0;
        for(int i=0;i<100;i++){
            int sum = a[i]+b[i]+up;
            if(sum<36){
                result[i] = sum;
                up = 0;
                continue;
            }
            result[i] = sum-36;
            up = 1;
        }
        return result;
    }
    public static int[] stringToNum(StringBuffer string){
        int[] result = new int[100];
        for(int i=0;i<string.length();i++){
            result[i] = charToNum(string.charAt(string.length()-i-1));
        }
        return result;
    }
    public static void charToZ(StringBuffer string, char c){
        for(int i=0;i<string.length();i++){
            if(string.charAt(i)==c){
                string.setCharAt(i,'Z');
            }
        }
    }

    public static char numToChar(int num){
        if(num<10){
            return (char)(num+48);
        }
        return (char)(num+55);
    }

    public static long bigger(char b,int place){
        return (long)charMinus('Z',b)*(long)Math.pow(36,place);
    }
    public static int charMinus(char _a,char _b){
        return charToNum(_a) - charToNum(_b);
    }
    public static int charToNum(char numChar){
        int tmp = (int)numChar;
        int num;
        if(tmp<=57){
            num = tmp-48;
        }
        else{
            num = tmp-55;
        }
        return num;
    }
}
