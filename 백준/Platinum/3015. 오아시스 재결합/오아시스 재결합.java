import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Main{
    public static class PeopleSameHeight{
        private int height;
        private long count;

        public PeopleSameHeight(int height,long count){
            this.height = height;
            this.count = count;
        }

        public PeopleSameHeight(int height){
            this.height = height;
            this.count = 1;
        }

        public void incCount(){
            this.count++;
        }

        public long getCount(){
            return count;
        }

        public int isHigher(PeopleSameHeight people){
            if(people.height<this.height){
                return 0;
            }
            else if(people.height>this.height){
                return 1;
            }
            return 2;
        }

    }


    //큐에 들어있는 것은 왼쪽으로 바라보았을 때 볼 수 있는 사람들을 저장한 것
    //오른쪽에서 큰 사람이 등장할때 왼쪽에 있는 걸 지우면 바라볼 수 있는 사람들을 저장할 수 있음.
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] line = new int[n];
        for(int i=0;i<n;i++){
            line[i] = Integer.parseInt(bf.readLine());
        }

        Deque<PeopleSameHeight> que = new ArrayDeque<>();
        que.add(new PeopleSameHeight(line[0]));
        long count = 0;

        for(int i=1;i<line.length;i++){
            PeopleSameHeight currentPeople = new PeopleSameHeight(line[i]);
            count+=removePeopleLower(que,currentPeople);

            if(que.isEmpty()){
                que.push(currentPeople);
//                System.out.println(count);
                continue;
            }

            int status = currentPeople.isHigher(que.peek());
            if(status==1){
                count++;
                que.push(currentPeople);
            }
            else if(status==2){
                count+=que.peek().getCount();
//                System.out.println("큐사ㅓ이즈"+que.size());
                if(que.size()>=2){
                    count++;
                }
                que.peek().incCount();
            }
//            System.out.println(count);
        }
        System.out.println(count);
    }
    public static long removePeopleLower(Deque<PeopleSameHeight> que,PeopleSameHeight currentPeople){
        long count=0;
        while (!que.isEmpty()){
            PeopleSameHeight closePeople = que.peek();
            int status = currentPeople.isHigher(closePeople);
            if(status==0){
                que.pop();
                count+=closePeople.getCount();
            }
            else{
                break;
            }
        }
        return count;
    }

}
