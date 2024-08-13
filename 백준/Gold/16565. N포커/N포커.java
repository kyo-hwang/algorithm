import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    static long[][] combinationTable;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        combinationTable = new long[49][49];

        for(int i=0;i<49;i++){
            combinationTable[i][0] = 1;
        }

        for(int i=1;i<49;i++){
            for(int j=1;j<=i;j++){
                combinationTable[i][j] = combinationTable[i-1][j-1]*i/j;
            }
        }

        int result = 0;
        for(int i=1;i<=13;i++){
            if(i%2==1){
                result = ((int)(getCombination(52-4*i,n-4*i)%10007)*(int)(getCombination(13,i)%10007)%10007+result)%10007;
            }
            else{
                result = (result - (int)(getCombination(52-4*i,n-4*i)%10007)*(int)(getCombination(13,i)%10007)%10007 + 10007)%10007;
            }
        }
        System.out.println(result);
    }

    public static long getCombination(int m,int n){
        if(n<0){
            return 0;
        }
        return combinationTable[m][n];
    }
    
}
