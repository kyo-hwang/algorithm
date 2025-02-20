import java.util.*;
class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        num--;
        
        while(num<=n-1){
            num=((num/w+1)*w-1-num)*2+1+num;
            // System.out.println(num);
            answer++;
            // if(answer>10){
            //     return answer;
            // }
        }
        
        return answer;
    }
}