import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static int[] post;
    public static int[] in;
    public static int[] inNodeLoc;
    public static int[] pre;
    public static int preLoc=0;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        in = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        inNodeLoc = new int[n+1];
        for(int i=0;i<n;i++){
            inNodeLoc[in[i]] = i;
        }

        post = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        pre = new int[n];
        StringBuffer result = new StringBuffer();

        circuitPre(0,n-1,0,n-1);
        for(int i=0;i<n;i++){
            result.append(pre[i]+" ");
//            System.out.print(pre[i]+" ");
        }
        System.out.println(result);
    }
    public static void circuitPre(int postStart,int postEnd,int inStart,int inEnd){
        int root = post[postEnd];
        pre[preLoc] = root;
        preLoc++;
//        System.out.println(Arrays.toString(pre));
        if(postStart==postEnd){
            return;
        }

        int boundInNode = inNodeLoc[root];
        if(boundInNode==inEnd){
            circuitPre(postStart,postEnd-1,inStart,inEnd-1);
            return;
        }
        if(boundInNode==inStart){
            circuitPre(postStart,postEnd-1,inStart+1,inEnd);
            return;
        }

        int leftInterval = boundInNode-1-inStart;
        circuitPre(postStart,postStart+leftInterval,inStart,boundInNode-1);
        circuitPre(postStart+leftInterval+1,postEnd-1,boundInNode+1,inEnd);
    }
}
