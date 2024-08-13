import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main{
    public static class Rod{
        private long height;
        private long width;

        public Rod(long height, long width){
            this.height = height;
            this.width = width;
        }

        public long getExtent(){
            return height*width;
        }

        public boolean isHeightSmallEqual(long height){
            if(this.height<=height){
                return true;
            }
            return false;
        }

        public void increaseWidth(Rod rod){
            this.width+=rod.width;
        }
        public void increaseWidth(long width){
            this.width+=width;
        }

        public long getWidth(){
            return width;
        }

        @Override
        public String toString() {
            return "height :"+height+" width "+width;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            long[] histograms = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            if(histograms[0]==0&&histograms.length==1){
                return;
            }
            long[] histogram = Arrays.copyOfRange(histograms,1,histograms.length);
            Deque<Rod> stack = new ArrayDeque<>();
            long result = 0;
            for(long stick:histogram){
//                System.out.println(stack);
                if(stack.isEmpty()){
                    stack.push(new Rod(stick,1));
                }
                else{
                    if(stack.getFirst().isHeightSmallEqual(stick)){
                        stack.push(new Rod(stick,1));
                    }
                    else{
                        long width = 0;
                        Rod rod = new Rod(stick,1);
                        while(!stack.isEmpty()&&!stack.getFirst().isHeightSmallEqual(stick)){
                            Rod beforeRod = stack.pop();
                            rod.increaseWidth(beforeRod);

                            beforeRod.increaseWidth(width);
//                            System.out.println(width);
                            result = Math.max(result,beforeRod.getExtent());
                            width = beforeRod.getWidth();
                        }
                        stack.push(rod);
                    }
                }
            }

//            System.out.println(stack);
            long width = 0;
            while(!stack.isEmpty()){
//                System.out.println(stack);
                Rod rod = stack.pop();
                rod.increaseWidth(width);
                result = Math.max(result,rod.getExtent());
                width = rod.getWidth();
//                System.out.println(rod);
//                System.out.println(result);
            }

            System.out.println(result);
        }
    }
}
