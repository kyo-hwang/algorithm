import java.util.*;

class Solution {

    Map<String,List<Ticket>> mapDepartureToTicket;
    int ticketNum;
    List<String> possiblePath = new ArrayList<>();
    
    public class Ticket{
        public Ticket(String[] ticketInfo){
            departure = ticketInfo[0];
            destination = ticketInfo[1];
            isUsed = false;
        }
        
        String departure;
        String destination;
        boolean isUsed;
        
        public String getDestination(){
            return destination;
        }
        
        public boolean isUsed(){
            return isUsed;
        }
        
        public void use(){
            isUsed = true;
        }
        
        public void republication(){
            isUsed = false;
        }
    }

    public String[] solution(String[][] tickets) {
        
        mapDepartureToTicket = new HashMap<>();
        ticketNum = tickets.length;
        
        for(String[] ticketInfo:tickets){
            if(!mapDepartureToTicket.containsKey(ticketInfo[0])){
                mapDepartureToTicket.put(ticketInfo[0],new ArrayList<>());
            }
            mapDepartureToTicket.get(ticketInfo[0]).add(new Ticket(ticketInfo));
        }
        
        dfs("ICN",0,new StringBuffer("ICN "));

        Collections.sort(possiblePath);

        return possiblePath.get(0).split(" "); 
    }
    
    public void dfs(String departure, int depth, StringBuffer buffer){
        if(depth==ticketNum){
            possiblePath.add(buffer.toString());
            return;
        }
        //[["ICN", "JFK"], ["ICN", "AAD"], ["JFK", "ICN"]]
        if(!mapDepartureToTicket.containsKey(departure)){
            return;
        }
        for(Ticket ticket : mapDepartureToTicket.get(departure)){
            if(!ticket.isUsed()){
                ticket.use();
                buffer.append(ticket.getDestination()).append(" ");
                dfs(ticket.getDestination(),depth+1,buffer);
                buffer.delete(buffer.length()-4,buffer.length());
                ticket.republication();
            }
        }
    }

//     static void dfs(int depth, String now, String path, String[][] tickets){
//         if (depth == tickets.length) {
//             list.add(path);
//             return;
//         }

//         for (int i = 0; i < useTickets.length; i++) {
//             if (!useTickets[i] && now.equals(tickets[i][0])) {
//                 useTickets[i] = true;
//                 dfs(depth+1, tickets[i][1], path + " " +tickets[i][1], tickets);
//                 useTickets[i] = false;
//             }
//         }
//     }
}