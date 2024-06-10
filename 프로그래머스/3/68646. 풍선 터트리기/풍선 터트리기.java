import java.util.*;

class Solution {
    public int solution(int[] a) {
        HashSet<Integer> hs = new HashSet<>();
        int i1 = Integer.MAX_VALUE;
        int i2 = Integer.MAX_VALUE;
        
        for(int i = 0; i < a.length; i++){
            i1 = Math.min(i1, a[i]);
            i2 = Math.min(i2, a[a.length - 1 - i]);
            
            hs.add(i1);
            hs.add(i2);
        }
        return hs.size();
    }
}