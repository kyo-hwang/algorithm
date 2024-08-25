import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    public static class VisitInfo{
        public VisitInfo(int loc,int time){
            this.loc = loc;
            this.time = time;
        }

        int loc;
        int time;

        public int getLoc(){
            return loc;
        }
        public int getTime(){
            return time;
        }
    }
    public static int MAX=510000;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int result = -1;

        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int sisterLoc = input[0];
        int brotherLoc = input[1];

        boolean[] oddVisited = new boolean[500001];
        boolean[] evenVisited = new boolean[500001];
        Deque<VisitInfo> que = new ArrayDeque<>();
        que.offer(new VisitInfo(sisterLoc,0));
        if(sisterLoc==brotherLoc){
            System.out.println(0);
            return;
        }
        evenVisited[sisterLoc] = true;

        while(!que.isEmpty()){
            VisitInfo visitInfo = que.poll();
            int nextTime = visitInfo.getTime()+1;

            int twice = visitInfo.getLoc()*2;
            int plus = visitInfo.getLoc()+1;
            int minus = visitInfo.getLoc()-1;

            int nextBrotherLoc = brotherLoc+(nextTime+1)*nextTime/2;

            if(nextBrotherLoc>500000){
                break;
            }

            if(nextTime%2==1){
                if(twice<500001&&!oddVisited[twice]){
                    que.offer(new VisitInfo(twice,nextTime));
                    oddVisited[twice] = true;
                }
                if (plus<500001&&!oddVisited[plus]) {
                    que.offer(new VisitInfo(plus,nextTime));
                    oddVisited[plus] = true;
                }
                if (minus>=0&&minus<500001&&!oddVisited[minus]) {
                    que.offer(new VisitInfo(minus,nextTime));
                    oddVisited[minus] = true;
                }
                if(oddVisited[nextBrotherLoc]){
                    result = nextTime;
                    break;
                }
            }
            else{
                if(twice<500001&&!evenVisited[twice]){
                    que.offer(new VisitInfo(twice,nextTime));
                    evenVisited[twice] = true;
                }
                if (plus<500001&&!evenVisited[plus]) {
                    que.offer(new VisitInfo(plus,nextTime));
                    evenVisited[plus] = true;
                }
                if (minus>=0&&minus<500001&&!evenVisited[minus]) {
                    que.offer(new VisitInfo(minus,nextTime));
                    evenVisited[minus] = true;
                }
                if(evenVisited[nextBrotherLoc]){
                    result = nextTime;
                    break;
                }
            }
        }

        System.out.println(result);

    }
}
