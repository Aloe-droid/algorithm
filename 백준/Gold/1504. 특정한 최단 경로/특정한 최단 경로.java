import java.io.*;
import java.util.*;

class Main {
    static int N, E, max = 100_000_000;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][N + 1];
        for(int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], max);
            dp[i][i] = 0;
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            dp[x][y] = Math.min(dp[x][y], z);
            dp[y][x] = Math.min(dp[y][x], z);
        }

        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        int value = Math.min(dp[1][u] + dp[u][v] + dp[v][N], dp[1][v] + dp[v][u] + dp[u][N]);
        System.out.println(value < max ? value : -1);
    }
}