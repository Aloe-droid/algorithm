import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int[][] dp = new int[n + 1][n + 1];
        for(int[] r : results) {
            dp[r[0]][r[1]] = 1;
            dp[r[1]][r[0]] = -1;
        }
        
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                   if(dp[i][k] == 1 && dp[k][j] == 1) {
                       dp[i][j] = 1;
                       dp[j][i] = -1;
                    }
                    
                   if(dp[i][k] == -1 && dp[k][j] == -1) {
                       dp[i][j] = -1;
                       dp[j][i] = 1;
                   } 
                }
            }
        }
        
        int ans = 0;
        for(int i = 1; i <= n; i++) {
            int cnt = 0;
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                if(dp[i][j] != 0) cnt++;
            }
            if(cnt == n - 1) ans++;
        }
        
        return ans;
    }
}