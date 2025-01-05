import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        for(int i=0;i<t;i++){
            String num = bf.readLine();
            calResult(num);
        }
    }

    public static void calResult(String num){
        int[] number = new int[num.length()];
        for(int i=number.length-1;i>=0;i--){
            number[i] = num.charAt(number.length-1-i)-48;
        }
        int[] numCubing = new int[num.length()];

        if(number[0]==1){
            numCubing[0] = 1;
        }else if(number[0]==3){
            numCubing[0] = 7;
        }else if(number[0]==7){
            numCubing[0] = 3;
        }else{
            numCubing[0] = 9;
        }

        int[] sum = new int[num.length()];
        plus(sum,numCubing[0]*numCubing[0]*numCubing[0],0);
        int[] result = back(1,number,numCubing,sum);
        boolean isFirst = true;
        for(int i=result.length-1;i>=0;i--){
            if(result[i]==0){
                if(isFirst){
                    continue;
                }
            }
            System.out.print(result[i]);
            isFirst = false;
        }
        System.out.println();
    }

    public static int[] back(int depth ,int[] number ,int[] numberCubing,int[] sum){
        if(depth>=number.length){
            return numberCubing;
        }

        int cur = 0;
        for(int i=depth-1;i>=0;i--){
            for(int j=depth-i;j>=0;j--){
                if(j>i){
                    continue;
                }
                int k = depth-i-j;
                if(k>j){
                    break;
                }
                if((i==j)&&(i==k)){
                    cur+=numberCubing[(depth/3)]*numberCubing[(depth/3)]*numberCubing[(depth/3)];
                }else if(i != j && j != k){
                    cur+=6*numberCubing[i]*numberCubing[j]*numberCubing[k];
                }else{
                    cur+=3*numberCubing[i]*numberCubing[j]*numberCubing[k];
                }
            }
        }
        plus(sum,cur,depth);

        int multipleNum = 3*(int)Math.pow(numberCubing[0],2);

        for(int i=0;i<10;i++){
            if((multipleNum*i+sum[depth])%10==number[depth]){
                int[] newNumberCubing = new int[number.length];
                int[] newSum = new int[number.length];
                System.arraycopy(numberCubing,0,newNumberCubing,0,numberCubing.length);
                System.arraycopy(sum,0,newSum,0,numberCubing.length);
                plus(newSum,multipleNum*i,depth);
                newNumberCubing[depth] = i;
                int[] result = back(depth+1,number,newNumberCubing,newSum);
                if(result!=null){
                    return result;
                }
            }
        }
        return null;
    }

    public static void plus(int[] number,int numToPlus,int from){
        int upNum = 0;
        while(numToPlus!=0){
            if(from>=number.length){
                break;
            }
            int curResult = number[from]+numToPlus%10+upNum;
            upNum = curResult/10;
            curResult %=10;
            number[from] = curResult;
            from++;
            numToPlus /=10;
        }
        if(from<number.length){
            number[from]+=upNum;
        }
    }

}
