import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 0; i <= n; i++) Arrays.fill(dp[i], 100_000_000);
        
        for(int[] fare : fares){
            dp[fare[0]][fare[1]] = fare[2];
            dp[fare[1]][fare[0]] = fare[2];
        }
        
        for(int i = 1; i <= n; i++) dp[i][i] = 0;
        
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        
        int ans = dp[s][a] + dp[s][b];
        for(int i = 1; i <= n; i++){
            // 출발지 부터 i 지점까지 동행, i 지점에서 각자 비용
            int temp = dp[s][i] + dp[i][a] + dp[i][b];
            ans = Math.min(ans, temp);
        }
        return ans;
    }
}