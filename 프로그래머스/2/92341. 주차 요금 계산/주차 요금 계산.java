import java.util.*;

class Solution {
    static Map<String, Integer> hm;
    static Map<String, Integer> tm;
    static int max = 23 * 60 + 59;
    
    public int[] solution(int[] fees, String[] records) {
        hm = new TreeMap<>();
        tm = new TreeMap<>();
        
        for(String record : records){
            StringTokenizer st = new StringTokenizer(record);
            String date = st.nextToken();
            String[] times = date.split(":");
            int hour = Integer.parseInt(times[0]);
            int minute = Integer.parseInt(times[1]);
            
            int time = hour * 60 + minute;
            String num = st.nextToken();
            
            if(hm.containsKey(num)){
                int t = time - hm.get(num);
                tm.put(num, tm.getOrDefault(num, 0) + t);
                hm.remove(num);
            }else{
                hm.put(num, time);
            }
        }
        
        for(String num : hm.keySet()){
            int time = max - hm.get(num);
            tm.put(num, tm.getOrDefault(num, 0) + time);
        }
        
        List<Integer> list = new ArrayList<>();
        for(String s : tm.keySet()){
            int time = tm.get(s);
            if(fees[0] >= time) list.add(fees[1]);
            else{
                int remain = time - fees[0];
                int price = (int) Math.ceil((double) remain / fees[2]) * fees[3] + fees[1];
                list.add(price);
            }
        }
        
        return list.stream().mapToInt(i -> i).toArray();
    }
}