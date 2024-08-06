import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String string = bf.readLine().trim();
        List<Set<Integer>> palindromes = new ArrayList<>();
        for(int i=0;i<string.length();i++){
            palindromes.add(new HashSet<>());
        }

        for(int i=1;i<string.length()-1;i++){
            int time = Math.min(i,string.length()-i-1);
            for(int j=1;j<=time;j++){
                if(string.charAt(i-j)==string.charAt(i+j)){
                    palindromes.get(i+j).add(i-j);
                }
                else{
                    break;
                }
            }
            time = Math.min(i,string.length()-i);
            for(int j=1;j<=time;j++){
                if(string.charAt(i+j-1)==string.charAt(i-j)){
                    palindromes.get(i+j-1).add(i-j);
                }
                else{
                    break;
                }
            }
        }
        if(string.charAt(string.length()-1)==string.charAt(string.length()-2)){
            palindromes.get(string.length()-1).add(string.length()-2);
        }


        int[] dp = new int[string.length()+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1;i<string.length()+1;i++){
            for(Integer before:palindromes.get(i-1)){
                dp[i] = Math.min(dp[before]+1,dp[i]);
            }
            dp[i] = Math.min(dp[i-1]+1,dp[i]);
        }

        System.out.println(dp[string.length()]);
    }
}
