import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main{
    public static int pointC;
    public static class RouteStartBigger{
        int id;
        int start;
        int end;
        public RouteStartBigger(int id,int start,int end){
            this.id = id;
            this.start = start;
            this.end = end;
        }

        public String toString(){
            return this.id+" start : "+this.start+" end : "+this.end;
        }

    }


    public static class Route{
        int id;
        int start;
        int end;
        boolean isStartBigger;
        public Route(int id,int start,int end,boolean doesContainZero){
            this.id = id;
            this.start = start;
            this.end = end;
            this.isStartBigger = doesContainZero;
        }

        public static void addRouteListStartBigger(List<Route> routes,RouteStartBigger routeStartBigger){
            if(routeStartBigger.start!=pointC){
                routes.add(new Route(routeStartBigger.id,routeStartBigger.start,pointC,true));
            }
            if(routeStartBigger.end!=0){
                routes.add(new Route(routeStartBigger.id,0,routeStartBigger.end,true));
            }
        }

        public String toString(){
            return "id "+id+" start : "+start+" end : "+end;
        }

    }


    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        pointC = Integer.parseInt(bf.readLine())-1;
        int routeC = Integer.parseInt(bf.readLine());
        List<RouteStartBigger> routesContainingZero = new ArrayList<>();
        List<Route> routes = new ArrayList<>();

        for(int i=0;i<routeC;i++){
            int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if(input[0]>input[1]){
                routesContainingZero.add(new RouteStartBigger(i+1,input[0],input[1]));
            }
            else{
                routes.add(new Route(i+1,input[0],input[1],false));
            }
        }
        List<Integer> result = new ArrayList<>();

        routesContainingZero.sort((e1,e2)->{
            if(e1.start>e2.start){
                return 1;
            }
            else if(e1.start==e2.start){
                if(e1.end<e2.end){
                    return 1;
                }
            }
            return -1;
        });

//        System.out.println(routesContainingZero);

        int curMaxEnd = -1;
        for(RouteStartBigger route:routesContainingZero){
            if(route.end>curMaxEnd){
                result.add(route.id);
                Route.addRouteListStartBigger(routes,route);
                curMaxEnd = route.end;
            }
        }
//        System.out.println(result);

        routes.sort((e1,e2)->{
            if(e1.end<e2.end){
                return 1;
            } else if (e1.end==e2.end) {
                if(e1.start>e2.start){
                    return 1;
                } else if (e1.start==e2.start) {
                    if(!e1.isStartBigger&&e2.isStartBigger){
                        return 1;
                    }
                }
            }
            return -1;
        });
//        System.out.println(routes);

        int curMinStart = Integer.MAX_VALUE;
        for(Route route:routes){
            if(route.start<curMinStart){
                if(!route.isStartBigger){
                    result.add(route.id);
                }
                curMinStart = route.start;
            }
        }

        result.sort((e1,e2)->e1-e2);

        for(int id:result){
            System.out.print(id+" ");
        }
        System.out.println();
    }
}
