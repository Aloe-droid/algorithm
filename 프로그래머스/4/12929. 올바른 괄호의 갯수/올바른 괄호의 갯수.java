import java.util.*;

class Solution {
    static int N, cnt = 0;
    static int[] ints;
    
    public int solution(int n) {
        N = n * 2;
        ints = new int[N];
        dfs(0, 0);
        return cnt;
    }
    
    public static void dfs(int idx, int alpha) {
        if(idx == N) {
            if(alpha == 0) cnt++;
            return;
        }
        
        dfs(idx + 1, alpha + 1);
        if(alpha > 0) dfs(idx + 1, alpha - 1);
    }
}