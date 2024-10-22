import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static int[] dRow = {0,1,1,1,0,-1,-1,-1};
    public static int[] dCol = {1,1,0,-1,-1,-1,0,1};
    public static int result = 0;
    public static int max=-1;
    public static String resultString ="";
    public static int num=0;
    public static class Node{
        public Node(int depth){
            isWordLast = false;
            isFound = false;
            childs = new HashMap<>();
            this.depth = depth;
        }

        public boolean isWordLast;
        public boolean isFound;
        public HashMap<Character,Node> childs;
        public int depth;

        public String toString(){
            return childs.toString()+isWordLast;
        }


    }

    public static Node root;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        root = new Node(0);

        for(int i=0;i<n;i++){
            String input = bf.readLine();

            Node cur = root;

            for(int j=0;j<input.length();j++){
                if(!cur.childs.containsKey(input.charAt(j))){
                    cur.childs.put(input.charAt(j),new Node(j+1));
                }
                cur = cur.childs.get(input.charAt(j));
                if(j==(input.length()-1)){
                    cur.isWordLast = true;
                }
            }
        }
        bf.readLine();

        int t = Integer.parseInt(bf.readLine());
        for(int i=0;i<t;i++){
            char[][] board = new char[4][4];
            for(int j=0;j<4;j++){
                String input = bf.readLine();
                for(int k=0;k<4;k++){
                    board[j][k] = input.charAt(k);
                }
            }


            if(i!=t-1){
                bf.readLine();
            }
            Set<String> set = new HashSet<>();
            for(int j=0;j<4;j++){
                for(int k=0;k<4;k++){
                    if(!root.childs.containsKey(board[j][k])){
                        continue;
                    }
                    boolean[][] bo = new boolean[4][4];
                    bo[j][k] = true;
                    StringBuffer string = new StringBuffer();
                    string.append(board[j][k]);
                    Node next = root.childs.get(board[j][k]);
                    if(next.isWordLast&&!set.contains(string.toString())){
                        set.add(string.toString());
                        num++;
                        if(1>max){
                            max = 1;
                            resultString = string.toString();
                        }
                        else if(1==max){
                            if(resultString.compareTo(string.toString())>0){
                                resultString = string.toString();
                            }
                        }
                    }
                    back(next, j,k,board,bo,string,set);
                }
            }
            if(num==0){
                System.out.println("0 0");
                return;
            }
            System.out.println(result+" "+resultString+" "+num);
            result = 0;
            num = 0;
            max = -1;
            resultString = "";
        }
    }
    public static void back(Node cur,int bRow,int bCol,char[][] board,boolean[][] visited,StringBuffer string,Set<String> set){
        if(cur.depth>8){
            return;
        }
        for(int i=0;i<8;i++){
            int cRow = bRow+dRow[i];
            int cCol = bCol+dCol[i];
            if(cRow>=4||cRow<0||cCol>=4||cCol<0||visited[cRow][cCol]){
                continue;
            }
            char curChar =board[cRow][cCol];
            if(!cur.childs.containsKey(curChar)){
                continue;
            }

            Node next = cur.childs.get(curChar);
            string.append(board[cRow][cCol]);
            if(next.isWordLast&&!set.contains(string.toString())){

                int score = getScore(next.depth);
                result+=score;
                num++;

                set.add(string.toString());

                if(next.depth>max){
                    max = next.depth;
                    resultString = string.toString();
                }
                else if(next.depth==max){
                    if(resultString.compareTo(string.toString())>0){
                        resultString = string.toString();
                    }
                }
            }

            visited[cRow][cCol] = true;
            back(next,cRow,cCol,board,visited,string,set);
            visited[cRow][cCol] = false;
            string.deleteCharAt(string.length()-1);
        }
    }
    public static int getScore(int length){
        if(length==3||length==4){
            return 1;
        }
        else if(length==5){
            return 2;
        }
        else if(length==6){
            return 3;
        }
        else if(length==7){
            return 5;
        }
        else if(length==8){
            return 11;
        }
        return 0;
    }
}
