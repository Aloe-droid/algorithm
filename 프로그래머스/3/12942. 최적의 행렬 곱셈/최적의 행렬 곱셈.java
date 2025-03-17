import java.util.*;

class Solution {
    static int N, max = Integer.MAX_VALUE;
    static int[][] dp;
    
    public int solution(int[][] mats) {
        init(mats);
        set(mats);
        return dp[0][N - 1];
    }
    
    public static void init(int[][] mats) {
        N = mats.length;
        dp = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            Arrays.fill(dp[i], max);
            dp[i][i] = 0;
        }
    }
    
    public static void set(int[][] mats) {
        for(int len = 2; len <= N; len++) {
            for(int st = 0; st <= N - len; st++) {
                int ed = st + len - 1;

                for(int mid = st; mid < ed; mid++) {
                    if(dp[st][mid] == max || dp[mid + 1][ed] == max) continue;
                    
                    int v = mats[st][0] * mats[mid][1] * mats[ed][1];
                    dp[st][ed] = Math.min(dp[st][ed], dp[st][mid] + dp[mid + 1][ed] + v);
                }
            }
        }
    }
}