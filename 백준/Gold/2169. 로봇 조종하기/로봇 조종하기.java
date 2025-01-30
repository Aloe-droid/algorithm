import java.io.*;
import java.util.*;

class Main {
    static int N, M, min = -100_000_000;
    static int[][] ints, dp;
    static int[] dx = {0, 0, 1}, dy = {1, -1, 0};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ints = new int[N][M];
        dp = new int[N][M];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                ints[i][j] = Integer.parseInt(st.nextToken());
                // dp[i][j] = -1;
            }
        }

        // System.out.println(dfs(0, 0));
        System.out.println(getDp());
        
    }

    public static int getDp(){
        dp[0][0] = ints[0][0];
        for(int i = 1; i < M; i++) dp[0][i] = dp[0][i - 1] + ints[0][i];

        for(int i = 1; i < N; i++) {
            int[] i1 = new int[M];
            int[] i2 = new int[M];

            for(int j = 0; j < M; j++) {
                i1[j] = Math.max(dp[i - 1][j], j != 0 ? i1[j - 1] : Integer.MIN_VALUE) + ints[i][j];
                i2[M - 1 - j] = Math.max(dp[i - 1][M - 1 - j], j != 0 ? i2[M - j] : Integer.MIN_VALUE) + ints[i][M - 1 - j];
            }

            for(int j = 0; j < M; j++) {
                dp[i][j] = Math.max(i1[j], i2[j]);
            }
        }
        return dp[N - 1][M - 1];
    }

    // top down TLE...
    public static int dfs(int x, int y) {
        if(x == N - 1 && y == M - 1) return ints[x][y];
        if(dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0;
        int v = Integer.MIN_VALUE;
        for(int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            v = Math.max(v, ints[x][y] + dfs(nx, ny));
        }
        return dp[x][y] = v;
    }
}