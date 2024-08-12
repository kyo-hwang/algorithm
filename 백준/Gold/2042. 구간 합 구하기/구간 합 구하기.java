import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static class Node{
        private int start;
        private int end;
        private long sum;
        public Node(int start,int end,long sum){
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
        public Node addNode(Node nodeToAdd){
            return new Node(this.start,nodeToAdd.end,this.sum+ nodeToAdd.sum);
        }
        public boolean isPanel(int start,int end){
            return (this.start == start) && (this.end == end);
        }
        public void changeSum(long diff){
            sum += diff;
        }
        public long getSum(){
            return sum;
        }
        public int getMid(){
            return (start+end)/2;
        }

        @Override
        public String toString() {
            return "start:"+start+" end:"+end+ " sum:"+sum;
        }
    }

    public static class SegmentTree{
        private Node[] nodes;
        private long[] array;
        public SegmentTree(long[] array){
            int height = (int)Math.ceil(Math.log(array.length)/Math.log(2));
            int size = (int)Math.pow(2,height);
            this.array = new long[size+1];
            System.arraycopy(array, 0, this.array, 1, array.length);
            this.nodes = new Node[size*2];
            initSegmentTree(1,1,size);
//            System.out.println(Arrays.toString(this.array));
//            System.out.println(Arrays.toString(nodes));
        }
//        private
        public Node initSegmentTree(int nodeIndex, int start, int end){
            if(start==end){
                nodes[nodeIndex] = new Node(start,end,array[start]);
                return nodes[nodeIndex];
            }
            Node leftNode = initSegmentTree(nodeIndex*2,start,(start+end)/2);
            Node rightNode = initSegmentTree(nodeIndex*2+1,(start+end)/2+1,end);
            nodes[nodeIndex] = leftNode.addNode(rightNode);
            return nodes[nodeIndex];
        }

        public void update(int n,long value){
            int nodeIndex = array.length-2+n;
            long diff = value - array[n];
            array[n] = value;
            recursiveUpdate(nodeIndex,diff);
        }

        private void recursiveUpdate(int nodeIndex,long diff){
            if(nodeIndex==1){
                nodes[nodeIndex].changeSum(diff);
                return;
            }
            nodes[nodeIndex].changeSum(diff);
            recursiveUpdate(nodeIndex/2,diff);
        }

        public long getPanelSum(int node,int start,int end){
            if(nodes[node].isPanel(start,end)){
                return nodes[node].getSum();
            }
            long sum;
            if(end<=nodes[node].getMid()){
                sum = getPanelSum(node*2,start,end);
            }
            else if(start>=nodes[node].getMid()+1){
                sum = getPanelSum(node*2+1,start,end);
            }
            else{
                sum = getPanelSum(node*2,start,nodes[node].getMid())+getPanelSum(node*2+1,nodes[node].getMid()+1,end);
            }
            return sum;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] cFirst = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long[] array = new long[cFirst[0]];
        for(int i=0;i<cFirst[0];i++){
            array[i] = Long.parseLong(bf.readLine());
        }
        SegmentTree tree = new SegmentTree(array);
//        System.out.println(Arrays.toString(tree.nodes));
        for(int i=0;i<cFirst[1]+cFirst[2];i++){
            long[] input = Arrays.stream(bf.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            if(input[0]==1){
                tree.update((int)input[1],input[2]);
//                System.out.println(Arrays.toString(tree.nodes));
            }
            else{
                System.out.println(tree.getPanelSum(1,(int)input[1],(int)input[2]));
            }
        }
    }
}
