import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static int n;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        Character[][] map = new Character[n][n];
        for(int i=0;i<n;i++){
            map[i] = Arrays.stream(bf.readLine().split("")).map(e->e.charAt(0)).toArray(Character[]::new);
        }
        System.out.println(back(map,0));
    }
    public static int back(Character[][] map,int depth){
        if(depth>=n){
            return Integer.MAX_VALUE;
        }
        int value = Integer.MAX_VALUE;
        reverse(map[depth]);
        value = Math.min(back(map,depth+1),value);
        reverse(map[depth]);
        value = Math.min(back(map,depth+1),value);
        value = Math.min(calMin(map),value);
        return value;
    }

    public static int calMin(Character[][] map){
        int min = 0;
        for(int i=0;i<map.length;i++){
            int colCount = 0;
            for(int j=0;j<map.length;j++){
                if(map[j][i]=='H'){
                    colCount++;
                }
            }
            min+=Math.min(colCount,map.length-colCount);
        }
        return min;
    }
    public static void reverse(Character[] row){
        for(int i=0;i<row.length;i++){
            if(row[i]=='H'){
                row[i] = 'C';
            }else{
                row[i] = 'H';
            }
        }
    }
}
