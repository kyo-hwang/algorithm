import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = bf.readLine();
        if(input.isEmpty()){
            System.out.println("");
            return;
        }
        char a = 'a';
        String[] notion = new String[input.length()];
        for(int i =0;i<input.length();i++){
            notion[i] = Character.toString(input.charAt(i));
        }
        Deque<String> que = new ArrayDeque<>();
        for(String e :notion){
            que.push(e);
            if(e.equals(")")){
                Deque<String> tempQue = new ArrayDeque<>();
                String tempE = que.pop();
                while(true){
                    tempE = que.pop();
                    if(tempE.equals("(")){
                        break;
                    }
                    tempQue.push(tempE);
                }
                Deque<String> tempQue2 = new ArrayDeque<>();
                String e1 = tempQue.poll();
                while(!tempQue.isEmpty()){
                    String operator = tempQue.poll();
                    String e2 = tempQue.poll();
                    if(operator.equals("+")||operator.equals("-")){
                        tempQue2.push(e1);
                        tempQue2.push(operator);
                        e1 = e2;
                    }
                    else if(operator.equals("*")||operator.equals("/")){
                        e1 = new String(new StringBuffer(e1).append(e2).append(operator));
                    }
                }
                tempQue2.push(e1);
                StringBuffer tempResult = new StringBuffer(tempQue2.pollLast());
                while(!tempQue2.isEmpty()){
                    String operator = tempQue2.pollLast();
                    String e2 = tempQue2.pollLast();
                    tempResult.append(e2);
                    tempResult.append(operator);
                }
                que.push(tempResult.toString());
            }
        }
        String e1 = que.pollLast();
        Deque<String> que2 = new ArrayDeque<>();
        while(!que.isEmpty()){
            String operator = que.pollLast();
            String e2 = que.pollLast();
            if(operator.equals("+")||operator.equals("-")){
                que2.push(e1);
                que2.push(operator);
                e1 = e2;
            }else if(operator.equals("*")||operator.equals("/")){
                e1 = e1.concat(e2).concat(operator);
            }
        }
        que2.push(e1);
        StringBuffer result = new StringBuffer(que2.pollLast());

        while(!que2.isEmpty()){
            String operator = que2.pollLast();
            String e2 = que2.pollLast();
            result.append(e2).append(operator);
        }
        System.out.println(result);
    }
}
