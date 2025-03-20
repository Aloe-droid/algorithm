import java.util.*;

class Solution {
    static int N;
    static Map<String, Integer> hm;
    
    public int[] solution(String[] gems) {
        init(gems);
        
        int i1 = 0, i2 = gems.length - 1;
        int left = 0, right = 0;
        while(left <= right && right < gems.length) {
            String add = gems[right++];
            hm.put(add, hm.getOrDefault(add, 0) + 1);
            if(hm.size() != N) continue;
            if(right - left - 1 < i2 - i1) {
                i2 = right - 1;
                i1 = left;
            }
            
            while(left <= right) {
                String s = gems[left++];
                hm.put(s, hm.get(s) - 1);
                if(hm.get(s) == 0) {
                    hm.remove(s);
                    break;
                }
                
                if(right - left - 1 < i2 - i1) {
                    i2 = right - 1;
                    i1 = left;
                }
            }
        }
        
        return new int[] {i1 + 1, i2 + 1};
    }
    
    public void init(String[] gems) {
        hm = new HashMap<>();
        for(String gem : gems) hm.put(gem, 0);
        N = hm.size();
        hm.clear();
    }
}