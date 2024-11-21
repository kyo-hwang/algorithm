import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        int eventCount = Integer.parseInt(bf.readLine());

        int[][] events = new int[eventCount][2];
        for(int i=0;i<eventCount;i++){
            events[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        List<HashMap<List<Integer>,Integer[]>> dp = new ArrayList<>();
        dp.add(new HashMap<>());
        dp.get(0).put(Arrays.asList(1,1),new Integer[]{calDistance(new int[]{n,n},events[0]),1,0,0});
        dp.get(0).put(Arrays.asList(n,n),new Integer[]{calDistance(new int[]{1,1},events[0]),2,0,0});
//        for(List<Integer>u: dp.get(0).keySet()){
//            System.out.println("key : "+u);
//            System.out.println(Arrays.toString(dp.get(0).get(u)));
//        }

        for(int i=1;i<eventCount;i++){
            HashMap<List<Integer>,Integer[]> beforePossibles = dp.get(i-1);
            HashMap<List<Integer>,Integer[]> currentPossibles = new HashMap<>();
            int[] eventLoc = events[i];
            int[] beforeEventLoc = events[i-1];
            for(List<Integer> possibleStartPoint: beforePossibles.keySet()){
                Integer[] pointInfo = beforePossibles.get(possibleStartPoint);

                List<Integer> firstLoc = new ArrayList<>();
                List<Integer> secondLoc = new ArrayList<>();
                if(pointInfo[1]==1){
                    firstLoc.add(possibleStartPoint.get(0));
                    firstLoc.add(possibleStartPoint.get(1));
                    secondLoc.add(beforeEventLoc[0]);
                    secondLoc.add(beforeEventLoc[1]);
                }
                else{
                    firstLoc.add(beforeEventLoc[0]);
                    firstLoc.add(beforeEventLoc[1]);
                    secondLoc.add(possibleStartPoint.get(0));
                    secondLoc.add(possibleStartPoint.get(1));
                }

                int distance = calDistance(new int[]{secondLoc.get(0),secondLoc.get(1)},eventLoc)+pointInfo[0];
                if(currentPossibles.containsKey(firstLoc)){
                    if(currentPossibles.get(firstLoc)[0]>distance){
                        currentPossibles.replace(firstLoc,new Integer[]{distance,1,possibleStartPoint.get(0),possibleStartPoint.get(1)});
                    }
                }
                else{
                    currentPossibles.put(firstLoc,new Integer[]{distance,1,possibleStartPoint.get(0),possibleStartPoint.get(1)});
                }

                distance = calDistance(new int[]{firstLoc.get(0),firstLoc.get(1)},eventLoc)+pointInfo[0];
                if(currentPossibles.containsKey(secondLoc)){
                    if(currentPossibles.get(secondLoc)[0]>distance){
                        currentPossibles.replace(secondLoc,new Integer[]{distance,2,possibleStartPoint.get(0),possibleStartPoint.get(1)});
                    }
                }
                else{
                    currentPossibles.put(secondLoc,new Integer[]{distance,2,possibleStartPoint.get(0),possibleStartPoint.get(1)});
                }
            }
//            for(List<Integer>u: currentPossibles.keySet()){
//                System.out.println("key : "+u);
//                System.out.println(Arrays.toString(currentPossibles.get(u)));
//            }
            dp.add(currentPossibles);
        }

        int min = Integer.MAX_VALUE;
        int group = 0;
        List<Integer> before = new ArrayList<>();
        List<Integer> order = new ArrayList<>();

//        System.out.println(dp);
        HashMap<List<Integer>,Integer[]> v = dp.get(dp.size()-1);
        for(List<Integer>u: v.keySet()){
            Integer[] point = v.get(u);
            if(point[0]<min){
                min = point[0];
                before = new ArrayList<>(Arrays.asList(point[2],point[3]));
                group = point[1];
            }
        }
        order.add(group);

        for(int i=dp.size()-2;i>=0;i--){
            HashMap<List<Integer>,Integer[]> point = dp.get(i);
            Integer[] curInfo = point.get(before);
            order.add(curInfo[1]);
            before = new ArrayList<>(Arrays.asList(curInfo[2],curInfo[3]));
        }
        System.out.println(min);
        for(int i=order.size()-1;i>=0;i--){
            if(order.get(i)==1){
                System.out.println(2);
            }
            else {
                System.out.println(1);
            }
        }

    }
    public static int calDistance(int[] start,int[] end){
        return Math.abs(start[0]-end[0])+Math.abs(start[1]-end[1]);
    }
}
