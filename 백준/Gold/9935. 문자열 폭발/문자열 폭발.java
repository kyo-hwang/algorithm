import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] string = br.readLine().trim().toCharArray();

        char[] explosionStr = br.readLine().trim().toCharArray();

        int next = 0;
        char last = explosionStr[explosionStr.length-1];

        StringBuffer answer = new StringBuffer();
        Deque<Deque<Character>> ex = new ArrayDeque<>();
        for(char c : string){
//            System.out.println(next);
            if(c==explosionStr[0]){
                if(explosionStr.length-1==0){
                    next = 0;
                    continue;
                }
                Deque<Character> dq = new ArrayDeque<>();
                ex.push(dq);
                ex.getFirst().push(c);
                next = 1;
            }
            else if(c==explosionStr[next]){
                if(c == last){
                    ex.pop();
                    if(ex.isEmpty()){
                        next = 0;
                    }
                    else{
                        next = ex.getFirst().size();
                    }
                }
                else{
                    ex.getFirst().push(c);
                    next++;
                }
            }
            else{
                if(ex.isEmpty()){
                    answer.append(c);
                    next = 0;
                }
                else{
                    while(!ex.isEmpty()){
                        Deque<Character> a =ex.pollLast();
                        while (!a.isEmpty()){
                            answer.append(a.pollLast());
                        }
                    }
                    answer.append(c);
                    next = 0;
                }
            }

        }
        while(!ex.isEmpty()){
            Deque<Character> a =ex.pollLast();
            while (!a.isEmpty()){
                answer.append(a.pollLast());
            }
        }
        if(answer.isEmpty()){
            System.out.println("FRULA");
        }
        else{
            System.out.println(answer);
        }
    }
}
