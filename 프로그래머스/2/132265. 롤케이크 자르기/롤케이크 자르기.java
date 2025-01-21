import java.util.*;

class Solution {
    static Map<Integer, Integer> hm1, hm2;
    public int solution(int[] topping) {
        hm1 = new HashMap<>();
        hm2 = new HashMap<>();
        for(int t : topping) hm2.put(t, hm2.getOrDefault(t, 0) + 1);
        
        int cnt = 0;
        for(int t : topping) {
            hm1.put(t, hm1.getOrDefault(t, 0) + 1);
            hm2.put(t, hm2.getOrDefault(t, 0) - 1);
            if(hm2.get(t) == 0) hm2.remove(t);
            
            if(hm1.keySet().size() == hm2.keySet().size()) cnt++;
        }
        return cnt;
    }
}