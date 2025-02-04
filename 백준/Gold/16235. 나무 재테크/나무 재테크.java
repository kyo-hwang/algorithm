import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main{
    public static class Local{
        List<Integer> trees = new ArrayList<>();
        int nutrition=5;
        public void nourish(int amount){
            nutrition += amount;
        }
        public void addTree(int age){
            trees.add(age);
        }

        public int getTreeCount(){
            return trees.size();
        }

        public void growTree(){
            trees.sort(Comparator.comparingInt(e -> e));
            int aliveCount;
            for(aliveCount=0;aliveCount<trees.size();aliveCount++){
                int curTree = trees.get(aliveCount);
                if(curTree<=nutrition){
                    nutrition-=curTree;
                    trees.set(aliveCount,curTree+1);
                }else{
                    break;
                }
            }
            List<Integer> newTrees = new ArrayList<>();
            int nutritionAdd = 0;
            for(int i=0;i<aliveCount;i++){
                newTrees.add(trees.get(i));
            }
            for(int i=aliveCount;i<trees.size();i++){
                nutritionAdd+= trees.get(i)/2;
            }
            trees = newTrees;
            nourish(nutritionAdd);
        }

        public int calParentTrees(){
            int parentCount=0;
            for(Integer tree:trees){
                if(tree%5==0){
                    parentCount++;
                }
            }
            return parentCount;
        }
        public String toString(){
            return "{nutrition : "+nutrition+", trees : "+trees+"}";
        }
    }
    public static int groundSize;
    public static int treeCount;
    public static int investmentTime;

    public static int[][] nutritionMachine;

    public static Local[][] ground;

    public static int[] dRow = {0,1,1,1,0,-1,-1,-1};
    public static int[] dCol = {1,1,0,-1,-1,-1,0,1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        groundSize = input[0];
        treeCount = input[1];
        investmentTime = input[2];

        nutritionMachine = new int[groundSize][groundSize];
        for(int i=0;i<groundSize;i++){
            nutritionMachine[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        ground = new Local[groundSize][groundSize];
        for(int i=0;i<groundSize;i++){
            for(int j=0;j<groundSize;j++){
                ground[i][j] = new Local();
            }
        }

        for(int i=0;i<treeCount;i++){
            input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ground[input[0]-1][input[1]-1].addTree(input[2]);
        }

        for(int curtime=0;curtime<investmentTime;curtime++){
            for(int i=0;i<groundSize;i++){
                for(int j=0;j<groundSize;j++){
                    ground[i][j].growTree();
                }
            }
            int[][] parentsAdded = new int[groundSize][groundSize];
            for(int i=0;i<groundSize;i++){
                for(int j=0;j<groundSize;j++){
                    int parentCount = ground[i][j].calParentTrees();
                    for(int k=0;k<8;k++){
                        int nearRow = i+dRow[k];
                        int nearCol = j+dCol[k];
                        if(nearRow<0||nearRow>=groundSize||nearCol<0||nearCol>=groundSize){
                            continue;
                        }
                        parentsAdded[nearRow][nearCol] += parentCount;
                    }
                }
            }
            for(int i=0;i<groundSize;i++){
                for(int j=0;j<groundSize;j++){
                    for(int k=0;k<parentsAdded[i][j];k++){
                        ground[i][j].addTree(1);
                    }
                }
            }
            for(int i=0;i<groundSize;i++){
                for(int j=0;j<groundSize;j++){
                    ground[i][j].nourish(nutritionMachine[i][j]);
                }
            }
        }
        int result = 0;
        for(int i=0;i<groundSize;i++){
            for(int j=0;j<groundSize;j++){
                result += ground[i][j].getTreeCount();
            }
        }
        System.out.println(result);
    }
}
