import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main{
    public static class Candidate{
        public Candidate(int id,int recommendNum,int when){
            this.id = id;
            this.recommendNum  =recommendNum;
            this.when = when;
        }
        int id;
        int recommendNum;
        int when;
    }

    public static int location(List<Candidate> curCandidate,int id){
        for(int i=0;i<curCandidate.size();i++){
            if(curCandidate.get(i).id == id){
                return i;
            }
        }
        return -1;
    }

    public static void removeMin(List<Candidate> curCandidates){
        curCandidates.sort((e1,e2)->e2.when-e1.when);
        curCandidates.sort((e1,e2)->e2.recommendNum-e1.recommendNum);
        int min = curCandidates.get(curCandidates.size()-1).recommendNum;

        curCandidates.remove(curCandidates.size()-1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        int k = Integer.parseInt(bf.readLine());
        int[] recommendOrder = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Candidate> curCandidates = new ArrayList<>();

        for(int i=0;i<k;i++){
            int loc = location(curCandidates,recommendOrder[i]);
            if(loc == -1){
                if(curCandidates.size()>=n){
                    removeMin(curCandidates);
                }
                curCandidates.add(new Candidate(recommendOrder[i],1,i));
            }
            else {
                curCandidates.get(loc).recommendNum+=1;
            }
        }

        curCandidates.sort((e1,e2)->e1.id-e2.id);

        for (Candidate c : curCandidates){
            System.out.print(c.id+" ");
        }
    }
}
