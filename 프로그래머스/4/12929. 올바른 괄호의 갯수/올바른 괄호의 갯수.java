class Solution {
    static int N, cnt = 0;
    
    public int solution(int n) {
        N = n * 2;
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