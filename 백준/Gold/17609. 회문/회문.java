import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        List<String> strings = new ArrayList<>();
        for(int i=0;i<n;i++){
            strings.add(bf.readLine());
        }
        for(String string:strings){
            System.out.println(checkPalindrome(string,0));
        }

    }

    public static int checkPalindrome(String string,int type){
        int left =0;
        int right=string.length()-1;
        while(left<=right){
            if(string.charAt(left)!=string.charAt(right)){
                if(type==0){
                    if(checkPalindrome(string.substring(left,right),1)==0||checkPalindrome(string.substring(left+1,right+1),1)==0){
                        return 1;
                    }else{
                        return 2;
                    }
                }else{
                    return 2;
                }
            }else{
                left++;
                right--;
            }
        }
        return 0;
    }
}

