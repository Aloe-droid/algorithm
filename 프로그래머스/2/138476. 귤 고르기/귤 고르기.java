import java.util.*;

class Solution {
    Guul[] guuls;
    public int solution(int k, int[] tangerine) {
        int size = tangerine[0];
        for(int t : tangerine) size = Math.max(size, t);
        guuls = new Guul[size + 1];
        for(int i = 0; i <= size; i++) guuls[i] = new Guul(i);
        for(int t : tangerine) guuls[t].cnt += 1;
        Arrays.sort(guuls, Comparator.comparingInt(g -> g.cnt));
        
        int now = 0;
        int ans = 0;
        
        for(int i = size; i >= 0; i--) {
            now += guuls[i].cnt;
            ans += 1;
            
            if(now >= k) break;
        }
        
        return ans;
    }
}

class Guul {
    int size, cnt;
    public Guul(int size) {
        this.size = size;
        cnt = 0;
    }
}