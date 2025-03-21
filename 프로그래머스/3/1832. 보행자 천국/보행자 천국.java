class Solution {
    int M, N, MOD = 20170805;
    int[] dx = { 1, 0 }, dy = { 0, 1 };
    int[][] map;
    int[][][] dp;
    
    public int solution(int m, int n, int[][] cityMap) {
        M = m;
        N = n;
        map = cityMap;
        dp = new int[M][N][2];
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < 2; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return dfs(0, 0, 0);
    }
    
    public int dfs(int x, int y, int dir) {
        if(x == M - 1 && y == N - 1) return 1;
        if(dp[x][y][dir] != -1) return dp[x][y][dir] % MOD;
        
        dp[x][y][dir] = 0;
        for(int k = 0; k < 2; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(nx < 0 || nx >= M || ny < 0 || ny >= N || map[nx][ny] == 1) continue;
            
            // 1. 0인 경우 
            if(map[x][y] == 0) dp[x][y][dir] += dfs(nx, ny, k) % MOD;
            // 2. 2인 경우 (직진만 가능)
            else if(map[x][y] == 2 && (dir == k || (x == 0 && y == 0))) dp[x][y][dir] += dfs(nx, ny, k) % MOD;
        }
        return dp[x][y][dir] % MOD;
    }
}