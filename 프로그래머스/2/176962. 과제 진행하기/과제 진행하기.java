import java.util.*;
class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = {};
        for(int i=0;i<plans.length;i++){
            String[] hourMinute = plans[i][1].split(":");
            plans[i][1] = Integer.toString
                (Integer.parseInt(hourMinute[0])*60+Integer.parseInt(hourMinute[1]));
        }
        //시간 순으로 오름차순 정렬
        Arrays.sort(plans,Comparator.comparingInt(e->Integer.parseInt(e[1])));
        
        ArrayDeque<String[]> stack = new ArrayDeque<>();
        List<String> tmp = new ArrayList<>();
        String curHome = plans[0][0];
        String curTime = plans[0][1];
        String timeWorking =plans[0][2];
        
        for(int i=1;i<plans.length;i++){
            if(Integer.parseInt(curTime)+Integer.parseInt(timeWorking)==Integer.parseInt(plans[i][1])){
                tmp.add(curHome);
            }else if(Integer.parseInt(curTime)+Integer.parseInt(timeWorking)>Integer.parseInt(plans[i][1])){
                Integer remain =Integer.parseInt(timeWorking)-
                    (Integer.parseInt(plans[i][1])-Integer.parseInt(curTime));
                stack.push(new String[]{curHome,Integer.toString(remain)});
            }else{
                tmp.add(curHome);
                int tmpCurTime = Integer.parseInt(curTime)+Integer.parseInt(timeWorking);
                while(!stack.isEmpty()){
                    String[] recent = stack.peek();
                    if(Integer.parseInt(recent[1])+tmpCurTime<=Integer.parseInt(plans[i][1])){
                        tmp.add(recent[0]);
                        tmpCurTime+=Integer.parseInt(recent[1]);
                        stack.pop();
                    }else{
                        recent[1] = Integer.toString(
                        Integer.parseInt(recent[1])-(Integer.parseInt(plans[i][1])-tmpCurTime));
                        break;
                    }
                }
            }
            curHome = plans[i][0];
            curTime = plans[i][1];
            timeWorking = plans[i][2];
        }
        tmp.add(plans[plans.length-1][0]);
        while(!stack.isEmpty()){
            tmp.add(stack.pop()[0]);
        }
        
        answer = tmp.stream().toArray(String[]::new);
        
        return answer;
    }
    
}