import java.util.*;

class Solution {
    int n;
    int[] works;
    Map<Integer, Integer> tm;
    
    public long solution(int n, int[] works) {
        this.n = n;
        this.works = works;
        int m = search();
        return get(m);
    }
    
    public int search() {
        int left = 0, right = 50000;
        while(left <= right) {
            int mid = (left + right) / 2;
            
            if(check(mid)) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }
    
    public boolean check(int m) {
        int sum = 0;
        for(int work : works) {
            if(work > m) sum += work - m;
        }
        
        return sum <= n;
    }
    
    public long get(int m) {
        int s = 0;
        tm = new TreeMap<>(Collections.reverseOrder());
        for(int work : works) {
            if(work > m) {
                s += work - m;
                tm.put(m, tm.getOrDefault(m, 0) + 1);
            }else{
                tm.put(work, tm.getOrDefault(work, 0) + 1);
            }
        }
        
        n -= s;
    
        for(int key : tm.keySet()) {
            if(n == 0) break;
            
            int value = tm.get(key);
            if(value > n) {
                tm.put(key, value - n);
                if(key - 1 > 0) tm.put(key - 1, tm.getOrDefault(key - 1, 0) + n);
                break;
            }else {
                n -= value;
                tm.remove(key);
            }
        }
        
        long ans = 0L;
        for(int key : tm.keySet()) ans += (long) Math.pow(key, 2) * tm.get(key);
        return ans;
    }
}