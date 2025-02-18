import java.util.*;
class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {10000000,20000000};
        int left = 0;
        int right = 0;
        int curSum = sequence[0];
        
        while(left<=right&&right<sequence.length){
            if(curSum>k){
                curSum-=sequence[left];
                left++;
            }else if(curSum<k){
                right++;
                if(right>=sequence.length){
                    break;
                }
                curSum+=sequence[right];
            }else{
                if(answer[1]-answer[0]>right-left){
                    answer[1]=right;
                    answer[0]=left;
                }else if(answer[1]-answer[0]==right-left){
                    if(left<answer[0]){
                        answer[1]=right;
                        answer[0]=left;
                    }
                }
                curSum-=sequence[left];
                left++;
            }
        }
        
        return answer;
    }
}