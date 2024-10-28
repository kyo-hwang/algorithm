import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        String[] numStrings = bf.readLine().split(" ");

        Arrays.sort(numStrings,(e1,e2)->{
            String shorter;
            String longer;
            boolean isFrontShort;
            if(e1.length()<=e2.length()){
                shorter = e1;
                longer = e2;
                isFrontShort = true;
            }
            else {
                shorter = e2;
                longer = e1;
                isFrontShort = false;
            }

            while(true){
                for(int i=0;i<shorter.length();i++){
                    if(shorter.charAt(i)>longer.charAt(i)){
                        if(isFrontShort){
                            return -1;
                        }
                        return 1;
                    }
                    if(shorter.charAt(i)<longer.charAt(i)){
                        if(isFrontShort){
                            return 1;
                        }
                        return -1;
                    }
                }
                if(longer.length()==shorter.length()){
                    return 0;
                }

                longer = longer.substring(shorter.length());
                if(longer.length()<shorter.length()){
                    String tmp = shorter;
                    shorter = longer;
                    longer = tmp;
                    isFrontShort = !isFrontShort;
                }
            }
        });

        if(numStrings[0].charAt(0)=='0'){
            System.out.println(0);
            return;
        }

        for (int i=0;i<numStrings.length;i++){
            System.out.print(numStrings[i]);
        }
    }
}
