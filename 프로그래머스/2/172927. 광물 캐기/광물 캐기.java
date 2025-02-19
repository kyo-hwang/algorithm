import java.util.*;
class Solution {
    public List<Map<String,Integer>> tiredsOf = new ArrayList<>();
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        for(int i=0;i<3;i++){
            tiredsOf.add(new HashMap<String,Integer>());
            tiredsOf.get(i).put("diamond",25/(int)Math.pow(5,2-i));
            tiredsOf.get(i).put("iron",Math.max(1,5/(int)Math.pow(5,2-i)));
            tiredsOf.get(i).put("stone",1);
        }
        answer= back(picks,0,0,minerals);
        return answer;
    }
    
    public int back(int[] picks,int n,int tired,String[] minerals){
        if(n>=minerals.length){
            return tired;
        }
        if(picks[0]==0&&picks[1]==0&&picks[2]==0){
            return tired;
        }
        int minTired=Integer.MAX_VALUE;
        for(int i=0;i<3;i++){
            if(picks[i]>=1){
                Map<String,Integer> tiredOf = tiredsOf.get(i);
                int nextTired = tired;
                for(int j=n;j<Math.min(n+5,minerals.length);j++){
                    nextTired+=tiredOf.get(minerals[j]);
                }
                
                int[] nextPicks = new int[3];
                for(int j=0;j<3;j++){
                    nextPicks[j] = picks[j];
                }
                nextPicks[i]--;
                
                minTired = Math.min(
                    back(nextPicks,Math.min(n+5,minerals.length),nextTired,minerals)
                    ,minTired);
            }
        }
        return minTired;
    }
}