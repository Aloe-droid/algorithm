import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int div = 1_000_000_007;
        int[][] dp = new int[n][m];
        for(int[] ps : puddles) dp[ps[1] - 1][ps[0] - 1] = -1;
        dp[0][0] = 1;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(dp[i][j] == -1) continue;
                
                int cnt = 0;
                if(i - 1 >= 0 && dp[i - 1][j] != -1) cnt += dp[i - 1][j] % div;
                if(j - 1 >= 0 && dp[i][j - 1] != -1) cnt += dp[i][j - 1] % div;
                dp[i][j] += cnt % div;
            }
        }
        
        return dp[n - 1][m - 1] % div;
    }
}