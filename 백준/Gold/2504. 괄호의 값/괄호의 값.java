import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String parentheses = bf.readLine();
        int v = calValue(parentheses);
        if(v==-1){
            System.out.println(0);
        }else{
            System.out.println(v);
        }
    }

    public static int calValue(String parenthesis){
        Deque<Character> stack = new ArrayDeque<>();
        int result =0;
        int left=0;
        for(int i=0;i<parenthesis.length();i++){
            if(parenthesis.charAt(i)==')'){
                if(stack.isEmpty()){
                    return -1;
                }else if(stack.peekFirst()!='('){
                    return -1;
                }
                stack.pop();
            }else if(parenthesis.charAt(i)==']'){
                if(stack.isEmpty()){
                    return -1;
                }else if(stack.peekFirst()!='['){
                    return -1;
                }
                stack.pop();
            }else{
                stack.push(parenthesis.charAt(i));
                continue;
            }

            if(stack.isEmpty()){
                if(i-left==1){
                    if(parenthesis.charAt(i)==')'){
                        result+=2;
                    }else{
                        result+=3;
                    }
                }
                else{

                    int v =calValue(parenthesis.substring(left+1,i));
                    if(v==-1){
                        return -1;
                    }
                    if(parenthesis.charAt(i)==')'){
                        result+=2*v;
                    }else{
                        result+=3*v;
                    }
                }
                left=i+1;
            }
        }
        if(!stack.isEmpty()){
            return -1;
        }
        return result;
    }
}
