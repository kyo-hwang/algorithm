import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        Map<String,Integer> rankOfRunner = new HashMap<>();
        for(int i=0;i<players.length;i++){
            rankOfRunner.put(players[i],i);
        }
        
        for(String calling:callings){
            int rank = rankOfRunner.get(calling);
            String frontRunner = players[rank-1];
            rankOfRunner.replace(calling,rank-1);
            rankOfRunner.replace(frontRunner,rank);
            
            players[rank-1] = calling;
            players[rank] = frontRunner;
        }
        
        answer = players;
        
        return answer;
    }
}