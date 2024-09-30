import java.util.*;
import java.io.*;
class Solution {
    public int[] emoticons;
    public int usersNum;
    public int maxPlus = -1;
    public int maxAmount = -1;
    public int[][] users;
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        usersNum = users.length;
        this.emoticons = emoticons;
        this.users = users;
        
        back(new int[usersNum],0);
        answer[0] = maxPlus;
        answer[1] = maxAmount;
        return answer;
    }
    
    public void back(int[] allPay,int depth){
        if(depth>=emoticons.length){
            // System.out.println(Arrays.toString(allPay));
            int[] tmp = findPlus(allPay);
            int plusNum = tmp[0];
            int totalAmount = tmp[1];
            
            if(plusNum<maxPlus){
                return;
            }
            if(plusNum==maxPlus){
                maxAmount = Math.max(maxAmount,totalAmount);
                return;
            }
            if(plusNum>maxPlus){
                maxPlus = plusNum;
                maxAmount = totalAmount;
                return;
            }
        }
        for(int i=10;i<=40;i+=10){
            int[] newAllPay = new int[usersNum];
            System.arraycopy(allPay,0,newAllPay,0,usersNum);
            int priceDiscounted = emoticons[depth]*(100-i)/100;
            // System.out.println(i);
            for(int j=0;j<usersNum;j++){
                if(users[j][0]<=i){
                    newAllPay[j] += priceDiscounted;
                }
            }
            // System.out.println(Arrays.toString(allPay));
            back(newAllPay,depth+1);
        }
    }
    
    public int[] findPlus(int[] allPay){
        int[] answer= new int[2];
        // System.out.println("allPay "+Arrays.toString(allPay));
        for(int i=0;i<usersNum;i++){
            if(users[i][1]<=allPay[i]){
                answer[0]++;
            }
            else{
                answer[1]+=allPay[i];
            }
        }
        // System.out.println("answer "+Arrays.toString(answer));
        return answer;
    }
}