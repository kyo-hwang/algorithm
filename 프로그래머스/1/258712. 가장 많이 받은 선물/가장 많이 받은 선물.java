import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int pN = friends.length;
        Map<String,Integer> map = new HashMap<>();
        
        for(int i=0;i<pN;i++){
            map.put(friends[i],i);
        }
        
        int[][] presentHistory = new int[pN][pN];
        int[] presentFigure = new int[pN];
        
        for(String gift:gifts){
            String[] giftRelation = gift.split(" ");
            int sender = map.get(giftRelation[0]);
            int receiver = map.get(giftRelation[1]);
            
            presentFigure[sender] ++;
            presentFigure[receiver] --;
            presentHistory[sender][receiver] ++;
        }
        
        for(int i=0;i<pN;i++){
            int presentToReceive = 0;
            for(int j=0;j<pN;j++){
                if(i==j) continue;
                //받은 선물이 준 선물보다 많다면
                if(presentHistory[j][i]>presentHistory[i][j]) continue;
                if(presentHistory[j][i]==presentHistory[i][j]){
                    if(presentFigure[i]>presentFigure[j]){
                        presentToReceive++;
                    }
                    continue;
                }
                
                presentToReceive ++;
            }
            answer = Math.max(answer,presentToReceive);
        }
        
        return answer;
    }
}