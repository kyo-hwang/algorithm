import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main{
    public static String main = "<main>";
    public static String mainClose = "</main>";
    public static String div = "<div>";
    public static String divClose = "</div>";
    public static String p = "<p>";
    public static String pClose = "</p>";
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String document = bf.readLine();
        Deque<String> context = new ArrayDeque<>();
        context.push(main);

        StringBuffer result = new StringBuffer();

        for(int i=6;i<document.length();){
            if(context.peekFirst().equals(main)){
                if(document.startsWith(mainClose, i)){
                    break;
                }
                int j=i+1;
                while(document.charAt(j)!='>'){
                    j++;
                }
                result.append("title : ").append(document, i + 12, j - 1).append("\n");
                context.push(div);
                i=j+1;
            }
            else if(context.peekFirst().equals(div)) {
                if (document.startsWith(divClose, i)) {
                    context.pop();
                    i += divClose.length();
                    continue;
                }
                context.push(p);
                i += p.length();
            }
            else{
                int j=i;
                while(!document.startsWith(pClose,j)){
                    j++;
                }
                if(i!=j){
                    String pContent = document.substring(i,j);
                    pContent = pContent.strip();
                    pContent = pContent.replaceAll("<([a-z]|[A-Z]| )*>","");
                    pContent = pContent.replaceAll("</([a-z]|[A-Z]| )*>","");
                    pContent = pContent.replaceAll(" {2,}"," ");
                    result.append(pContent).append("\n");
                }
                i=j+pClose.length();
                context.pop();
            }
        }
        System.out.println(result);
    }
}
