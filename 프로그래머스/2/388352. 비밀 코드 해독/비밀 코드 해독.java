import java.util.*;

class Solution {
    int n, cnt = 0;
    int[] ans;
    int[][] q;
    
    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        
        comb(1, 0, new int[5]);
        return cnt;
    }
    
    public void comb(int start, int idx, int[] ints) {
        if(idx == 5) {
            check(ints);
            return;
        }
        
        for(int i = start; i <= n; i++) {
            ints[idx] = i;
            comb(i + 1, idx + 1, ints);
        }
    }
    
    public void check(int[] ints) {
        int[] temp = new int[31];
        for(int i : ints) temp[i]++;
        for(int i = 0; i < q.length; i++) {
            for(int k : q[i]) temp[k]++;
            
            int c = 0;
            for(int t : temp) if(t == 2) c++;
            if(ans[i] != c) return;
            
            for(int k : q[i]) temp[k]--;
        }
        cnt++;
    }
}