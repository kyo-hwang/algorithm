import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        String line = bf.readLine();

        int len = 0;
        int result = 0;
        for(int i=0;i<m-2;i++){
            if(line.substring(i,i+3).equals("IOI")){
                len++;
                i+=1;
            }
            else{
                len = 0;
            }
            if(len >=n){
                result ++;
            }
        }
        System.out.println(result);
    }
}
