import java.util.*;

class Solution {
    static int N;
    static boolean flag;
    static Map<String, Integer> tickets;
    static Map<String, Integer> nowTickets;
    static Map<String, List<String>> hm;
    static List<String> list;
    
    public String[] solution(String[][] tickets) {
        N = tickets.length;
        init(tickets);
        dfs("ICN", 0);
        return list.toArray(new String[0]);
    }
    
    public static void dfs(String node, int count){
        if(count == N){
            flag = true;
            return;
        }
        
        for(String next : hm.getOrDefault(node, new ArrayList<>())){
            String dir = node + " -> " + next;
            
            int cnt = nowTickets.getOrDefault(dir, 0) + 1;
            if(cnt > tickets.getOrDefault(dir, 0)) continue;
            
            nowTickets.put(dir, cnt);
            list.add(next);
            
            dfs(next, count + 1);
            if(flag) return;
                 
            nowTickets.put(dir, cnt - 1);
            list.remove(list.size() - 1);
        }
    }
    
    public static void init(String[][] ticketArray) {
        list = new ArrayList<>();
        list.add("ICN");
        hm = new HashMap<>();
        tickets = new HashMap<>();
        nowTickets = new HashMap<>();
        
        for(String[] ticket : ticketArray){
            String key = ticket[0];
            String value = ticket[1];
            List<String> ls = hm.getOrDefault(key, new ArrayList<>());
            ls.add(value);
            hm.put(key, ls);
            
            String dir = key + " -> " + value;
            tickets.put(dir, tickets.getOrDefault(dir, 0) + 1);
        }
        
        for(String key : hm.keySet()){
            List<String> li = hm.get(key);
            Collections.sort(li);
        }
    }
}
