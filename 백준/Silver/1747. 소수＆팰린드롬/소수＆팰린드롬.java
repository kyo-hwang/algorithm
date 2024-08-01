import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        if(n==1){
            System.out.println(2);
            return;
        }
        while(true){
//            System.out.println(n);
            if(validatePrime(n)&&validatePalindrome(n)){
                break;
            }
            n++;
        }
        System.out.println(n);
    }

    public static boolean validatePrime(int n){
        int pow = (int)Math.sqrt(n);
        for(int i=2;i<=pow;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }

    public static boolean validatePalindrome(int n){
        String nString  = Integer.toString(n);
        for(int i=0;i<=nString.length()/2;i++){
            if(nString.charAt(i) !=nString.charAt(nString.length()-i-1)){
                return false;
            }
        }
        return true;
    }
}
