import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static long[][] dp;
    public static boolean[][] dpFilled;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        long[] input = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        int n = (int)input[0];
        long k = input[1];

        dp = new long[n+1][n+1];
        dpFilled = new boolean[n+1][n+1];

        dp[n][0]=fillDp(n,1,1);
        if(n==1){
            if(k>1){
                System.out.println(-1);
                return;
            }
            else if(k==1){
                System.out.println(")");
                return;
            }
            System.out.println("(");
            return;
        }

//        for(int i=0;i<n+1;i++){
//            System.out.println(Arrays.toString(dp[i]));
//        }
//        System.out.println((long)Math.pow(2,n)-1-dp[n][0]);
        if((long)Math.pow(2,n)-1-dp[n][0]<k){
            System.out.println(-1);
            return;
        }

        System.out.println(calParenthesis(k,0,new StringBuffer(),1,n,0));

    }
    public static long fillDp(int n, int depth,int frontCloseNum){
//        System.out.println(n-depth+" "+frontCloseNum+" "+ depth);
        if(depth>=n){
//            System.out.println("!!!");
            dpFilled[n-depth][frontCloseNum] = true;
            dp[n-depth][frontCloseNum] = 1;
            return 1;
        }
        if(dpFilled[n-depth][frontCloseNum]){
            return dp[n-depth][frontCloseNum];
        }
        long parenthesisNum = 0;
        if(frontCloseNum-1>=0){
            parenthesisNum += fillDp(n,depth+1,frontCloseNum-1);
        }
        if(n-(depth+1)>=frontCloseNum+1){
            parenthesisNum += fillDp(n,depth+1,frontCloseNum+1);
        }

        dpFilled[n-depth][frontCloseNum] = true;
        dp[n-depth][frontCloseNum] = parenthesisNum;

        return parenthesisNum;
    }

    public static StringBuffer calParenthesis(long k,long th,StringBuffer parenthesis,int depth,int n,int frontCloseNum){
        if(frontCloseNum<0){
            long inter = k-th;
            for(int i=depth;i<=n;i++){
//                System.out.println(inter);
                if(inter-(long)Math.pow(2,n-i)>=0){
                    inter-=(long)Math.pow(2,n-i);
                    parenthesis.append(")");
                    continue;
                }
                parenthesis.append("(");
            }
            return parenthesis;
        }
        if(depth==n){
            if(k==th){
                parenthesis.append("(");
            }
            else{
                parenthesis.append(")");
            }
            return parenthesis;
        }
//        System.out.println("front "+frontCloseNum);
        long middle = (long)Math.pow(2,n-depth)-dp[n-depth][frontCloseNum+1]+th;
//        System.out.println(middle+" "+dp[n-depth][frontCloseNum]+" "+depth);
        if(middle-1<k){
            parenthesis.append(")");
//            System.out.println(th+(long)Math.pow(2,n-depth)-dp[n-depth][frontCloseNum+1]);
//            System.out.println(parenthesis);
            return calParenthesis(k,middle,parenthesis,depth+1,n,frontCloseNum-1);
        }
        parenthesis.append("(");
//        System.out.println(th);
//        System.out.println(parenthesis);
        return calParenthesis(k,th,parenthesis,depth+1,n,frontCloseNum+1);
    }
}
