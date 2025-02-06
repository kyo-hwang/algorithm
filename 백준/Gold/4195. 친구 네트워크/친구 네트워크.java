import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        for(int i=0;i<t;i++){
            int relationCount = Integer.parseInt(bf.readLine());
            String[][] relations = new String[relationCount][2];
            for(int j=0;j<relationCount;j++){
                relations[j] = bf.readLine().split(" ");
            }
            Map<String,String> parents = new HashMap<>();
            for(String[] relation:relations){
                String p1 = relation[0];
                String p2 = relation[1];
                if(parents.containsKey(p1)&&parents.containsKey(p2)){
                    String p1Root = findRoot(parents,p1);
                    String p2Root = findRoot(parents,p2);
                    if(p1Root.equals(p2Root)){
                        System.out.println(Integer.parseInt(parents.get(p1Root)));
                        continue;
                    }
                    int p2RootCount = Integer.parseInt(parents.get(p2Root));
                    int p1RootCount = Integer.parseInt(parents.get(p1Root));
                    parents.replace(p2Root,p1Root);
                    String result = Integer.toString(p2RootCount+p1RootCount);
                    parents.replace(p1Root,result);
                    System.out.println(result);
                }else if(parents.containsKey(p1)){
                    String p1Root = findRoot(parents,p1);
                    parents.put(p2,p1Root);
                    String result = Integer.toString(Integer.parseInt(parents.get(p1Root))+1);
                    parents.replace(p1Root,result);
                    System.out.println(result);
                }else if(parents.containsKey(p2)) {
                    String p2Root = findRoot(parents,p2);
                    parents.put(p1,p2Root);
                    String result = Integer.toString(Integer.parseInt(parents.get(p2Root))+1);
                    parents.replace(p2Root,result);
                    System.out.println(result);
                }else{
                    parents.put(p1,"2");
                    parents.put(p2,p1);
                    System.out.println(2);
                }
//                System.out.println(parents);
            }
        }
    }
    public static String findRoot(Map<String,String> parents,String node){
        if(Character.isDigit(parents.get(node).charAt(0))){
            return node;
        }
        String root = findRoot(parents,parents.get(node));
        parents.replace(node,root);
        return root;
    }
}
