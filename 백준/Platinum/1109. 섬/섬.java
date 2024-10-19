import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int row;
    public static int col;
    public static String[][] map;
    private static boolean[][] visted;
    private static int[] dCol = {1,1,0,-1,-1,-1,0,1};
    private static int[] dRow = {0,1,1,1,0,-1,-1,-1};
    public static int[][] mapDesignated;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        row = input[0];
        col = input[1];
        map = new String[row][col];

        for(int i=0;i<row;i++){
            map[i] = bf.readLine().split("");
        }

        visted = new boolean[row][col];

        mapDesignated = new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                mapDesignated[i][j] = Integer.MAX_VALUE;
            }
        }
        int id = designateIsland();
        if(id ==0){
            System.out.println(-1);
            return;
        }
//        for(int i=0;i<row;i++){
//            System.out.println(Arrays.toString(mapDesignated[i]));
//        }

        int[] relation = checkContain(id);

        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0;i<relation.length;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<relation.length;i++){
            if(relation[i]==Integer.MAX_VALUE){
                continue;
            }
            graph.get(relation[i]).add(i);
        }

        int[] num = new int[relation.length];
        int max = -1;
        for(int i=0;i<relation.length;i++){
            int c = calParent(graph,i);
            max = Math.max(c,max);
            num[c] ++;
        }
//        System.out.println(Arrays.toString(relation));
        for(int i=0;i<=max;i++){
            System.out.print(num[i]+" ");
        }
    }

    public static int calParent(List<List<Integer>> graph, int id){
        if(graph.get(id).size()==0){
            return 0;
        }
        int max = -1;
        for(int child:graph.get(id)){
            max = Math.max(max,calParent(graph,child)+1);
        }
        return max;
    }

    public static int[] checkContain(int count){
        int[] relation = new int[count];
        boolean[] islandVisited = new boolean[count];

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(!visted[i][j]&&mapDesignated[i][j]!=Integer.MAX_VALUE&&!islandVisited[mapDesignated[i][j]]){
                    visted = new boolean[row][col];
                    findParent(relation,i,j,mapDesignated[i][j]);
//                    System.out.println("!!!!!!"+Arrays.toString(relation));
                    islandVisited[mapDesignated[i][j]] = true;
                }
            }
        }
        return relation;
    }

    public static void findParent(int[] relation, int sr, int sc,int id){
        Deque<List<Integer>> que = new ArrayDeque<>();
        que.offer(Arrays.asList(sr,sc));
        visted[sr][sc] = true;
        if(sr==0||sr==row-1||sc==0||sc==col-1){
            relation[id] = Integer.MAX_VALUE;
            return;
        }

        int[] meetCount = new int[relation.length];
        boolean[][] checkVisited = new boolean[row][col];
        int[] dRow = {0,1,0,-1};
        int[] dCol = {1,0,-1,0};

//        int[] conRow = {-1,1,1,-1};
//        int[] conCol = {1,1,-1,-1};

        while(!que.isEmpty()){
//            System.out.println(que);
            List<Integer> loc = que.poll();
            for(int i=0;i<4;i++){
                int nr = loc.get(0)+dRow[i];
                int nc = loc.get(1)+dCol[i];
                if(nr>=row||nr<0||nc>=col||nc<0){
                    continue;
                }
                if((mapDesignated[nr][nc]==id||mapDesignated[nr][nc]==Integer.MAX_VALUE)&&!visted[nr][nc]){
//                    System.out.println(id+" "+nr+"    "+nc);
                    if(nr==0||nr==row-1||nc==0||nc==col-1){
                        relation[id] = Integer.MAX_VALUE;
                        return;
                    }
                    que.offer(Arrays.asList(nr,nc));
//                    System.out.println(que);
                    visted[nr][nc] = true;
                }
                else{
                    if(mapDesignated[nr][nc]!=Integer.MAX_VALUE&&mapDesignated[nr][nc]!=id&&!checkVisited[nr][nc]){
                        meetCount[mapDesignated[nr][nc]]++;
                        checkVisited[nr][nc] = true;
                    }
                }
            }

//            for(int i=0;i<4;i++){
//                int nr = loc.get(0)+conRow[i];
//                int nc = loc.get(1)+conCol[i];
//                if(nr>=row||nr<0||nc>=col||nc<0){
//                    continue;
//                }
//
//            }

        }

        int max = 0;
        for(int i=0;i<meetCount.length;i++){
            if(meetCount[i]>max){
                max = meetCount[i];
                relation[id] = i;
            }
        }

    }

    public static int designateIsland(){
        int id = 0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(map[i][j].equals("x")&&!visted[i][j]){
                    bfs(id,i,j);
                    id++;
                }
            }
        }
        visted = new boolean[row][col];
        return id;
    }

    public static void bfs(int id,int sr,int sc){
        Deque<List<Integer>> que = new ArrayDeque<>();
        que.offer(new ArrayList<>(Arrays.asList(sr,sc)));
        visted[sr][sc] = true;
        mapDesignated[sr][sc] = id;

        while(!que.isEmpty()){
            List<Integer> loc = que.poll();
            for(int i=0;i<8;i++){
                int nr = loc.get(0)+dRow[i];
                int nc = loc.get(1)+dCol[i];
                if(nr>=row||nr<0||nc>=col||nc<0){
                    continue;
                }
                if(visted[nr][nc]){
                    continue;
                }
                if(map[nr][nc].equals(".")){
                    continue;
                }
                que.offer(new ArrayList<>(Arrays.asList(nr,nc)));
                visted[nr][nc] = true;
                mapDesignated[nr][nc] = id;
            }
        }
    }
}
