import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        //end 기준으로 정렬
        Arrays.sort(targets,(e1,e2)->e1[1]-e2[1]);
        
        int lastWeaponLoc = 0;
        
        for(int[] target : targets){
            if(target[0]>=lastWeaponLoc){
                answer++;
                lastWeaponLoc = target[1];
            }
        }
        
        
        return answer;
    }
    
}