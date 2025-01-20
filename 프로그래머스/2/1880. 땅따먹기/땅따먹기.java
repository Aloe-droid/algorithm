class Solution {
    int solution(int[][] land) {
        int N = land.length;
        int M = land[0].length;
        int[][] dp = new int[N][M];
        for(int i = 0; i < M; i++) dp[0][i] = land[0][i];
        
        for(int i = 1; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int max = 0;
                for(int k = 0; k < M; k++) {
                    if(j == k) continue;
                    max = Math.max(max, dp[i - 1][k]);
                }
                dp[i][j] = max + land[i][j];
            }
        }
        
        int max = 0;
        for(int i = 0; i < M; i++) max = Math.max(max, dp[N - 1][i]);
        return max;
    }
}