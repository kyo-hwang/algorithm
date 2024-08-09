import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    private static int n;
    private static String expression;
    private static long result =-Long.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        expression = bf.readLine().trim();
        if(n==1){
            System.out.println(intOfExpression(0));
            return;
        }

        backTracking(1,intOfExpression(0));
        backTracking(2,operation(expression.charAt(1),intOfExpression(0),intOfExpression(2)));
        System.out.println(result);
    }

    public static void backTracking(int opOrder, long sum){
        if(opOrder*2>=n){
            result = Math.max(result,sum);
            return;
        }
        backTracking(opOrder+1,operation(expression.charAt(opOrder*2-1),sum,intOfExpression(opOrder*2)));

        int bracketOpIndex = (opOrder+1)*2-1;
        if(bracketOpIndex>=n){
            return;
        }
        long bracketOpResult = operation(expression.charAt(bracketOpIndex),intOfExpression(bracketOpIndex-1),intOfExpression(bracketOpIndex+1));
        backTracking(opOrder+2,operation(expression.charAt(opOrder*2-1),sum,bracketOpResult));
    }

    private static long intOfExpression(int i){
        return (long)Character.getNumericValue(expression.charAt(i));
    }

    private static long operation(char op,long fNum ,long sNum ){
        if(op=='*'){
            return fNum*sNum;
        }
        else if(op=='-'){
            return fNum-sNum;
        }
        else{
            return fNum+sNum;
        }
    }
}
