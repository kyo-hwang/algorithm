import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        List<String> numbers = new ArrayList<>();

        for(int i=0;i<n;i++){
            numbers.add(bf.readLine().trim());
        }

        numbers.sort((e1,e2)->{
            if(e1.length()>e2.length()){
                return 1;
            }
            else if(e1.length()<e2.length()){
                return -1;
            }

            int e1Sum = 0;
            for(int j=0;j< e1.length();j++){
                if(Character.isDigit(e1.charAt(j))){
                    e1Sum+= Character.getNumericValue(e1.charAt(j));
                }
            }

            int e2Sum = 0;
            for(int j=0;j< e2.length();j++){
                if(Character.isDigit(e2.charAt(j))){
                    e2Sum+= Character.getNumericValue(e2.charAt(j));
                }
            }

            if(e1Sum!=e2Sum){
                return e1Sum-e2Sum;
            }

            for(int j=0; j<e2.length();j++){
                if(e1.charAt(j)!=e2.charAt(j)){
                    return e1.charAt(j)-e2.charAt(j);
                }
            }
            return 1;
        }
        );

        for (String number : numbers) {
            System.out.println(number);
        }
    }
}
