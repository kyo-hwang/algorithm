import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static class Node{
        public Node(int s,int e){
            this.s = s;
            this.e = e;
        }
        int s;
        int e;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
//        int[][] locations = new int[n][2];
        List<Node> locations  = new ArrayList<>();
        for(int i=0;i<n;i++){
            String[] c = bf.readLine().split(" ");

            locations.add(new Node(Integer.parseInt(c[0]),Integer.parseInt(c[1])));
//            locations[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
//        Arrays.sort(locations,(loc1,loc2)->loc1[0]-loc2[0]);
        locations.sort((loc1,loc2)->loc1.s- loc2.s);

        int maxLast = -1000000000;
        int curLength = 0;

        for(Node loc : locations){
            if(loc.s>=maxLast){
                curLength += loc.e-loc.s;
                maxLast = loc.e;
            }
            else{
                if(loc.e>=maxLast){
                    curLength += loc.e-maxLast;
                    maxLast = loc.e;
                }
            }
        }
        System.out.println(curLength);
    }
}
