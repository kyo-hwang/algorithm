import java.util.*;
class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        List<Deque<Integer>> allBox = new ArrayList<>();
        for(int i=0;i<w;i++){
            allBox.add(new ArrayDeque<Integer>());
        }
        
        boolean isLeft = true;
        
        int[] boxLocs = new int[n+1];
        
        int boxId=1;
        while(boxId<=n){
            if(isLeft){
                for(int i=0;i<w;i++){
                    allBox.get(i).push(boxId);
                    boxLocs[boxId] = i;
                    boxId++;
                    if(boxId>n){
                        break;
                    }
                }
                isLeft = false;
            }else{
                for(int i=w-1;i>=0;i--){
                    allBox.get(i).push(boxId);
                    boxLocs[boxId] = i;
                    boxId++;
                    if(boxId>n){
                        break;
                    }
                }
                isLeft=true;
            }
        }
        
        int boxLocWanted = boxLocs[num];
        
        while(allBox.get(boxLocWanted).pop()!=num){
            answer++;
        }
        answer++;
        
        return answer;
    }
}