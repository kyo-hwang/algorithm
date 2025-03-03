import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static String[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        map = new String[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(map[i]," ");
        }

        printStar(0,0,n-1,n-1);

        StringBuffer[] result = new StringBuffer[n];

        for(int i=0;i<n;i++){
            result[i] = new StringBuffer();
            for(int j=0;j<n;j++){
                result[i].append(map[i][j]);
            }
        }
        for(int i=0;i<n;i++){
            System.out.println(result[i]);
        }
    }
    public static void printStar(int lur,int luc,int rdr,int rdc){
        if(rdr-lur==2){
            for(int i=0;i<3;i++){
                map[lur][i+luc] = "*";
            }
            map[lur+1][luc] = "*";
            map[lur+1][luc+2] = "*";

            for(int i=0;i<3;i++){
                map[lur+2][i+luc] = "*";
            }
        }
        for(int i=lur;i<rdr;i+=(rdr-lur+1)/3){
            for(int j=luc;j<rdc;j+=(rdc-luc+1)/3){
                if(i==lur+(rdr-lur+1)/3&&j==luc+(rdc-luc+1)/3){
                    continue;
                }
                printStar(i,j,i+(rdr-lur+1)/3-1,j+(rdc-luc+1)/3-1);
            }
        }

    }
}
