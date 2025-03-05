import java.util.*;
class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        List<String> nameList = List.of(name);
        
        int i=-1;
        //그리움 점수가 없는 인물도 존재
        for(String[] names:photo){
            i++;
            int score=0;
            for(String oneName:names){
                int index = nameList.indexOf(oneName);
                if(index==-1){
                    continue;
                }
                score+=yearning[index];
            }
            answer[i] = score;
        }
        return answer;
    }
}