import java.util.*;

class Solution {
    public int solution(int[] a) {
        HashSet<Integer> hs = new HashSet<>();
        TreeSet<Integer> ts1 = new TreeSet<>();
        TreeSet<Integer> ts2 = new TreeSet<>();
        
        ts1.add(a[0]);
        for(int i = 1; i < a.length; i++) ts2.add(a[i]);
        
        hs.add(ts1.first());
        hs.add(ts2.first());
        
        for(int i = 1; i < a.length - 1; i++){
            ts1.add(a[i]);
            ts2.remove(a[i]);
            
            hs.add(ts1.first());
            hs.add(ts2.first());
        }
        return hs.size();
    }
}