import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] ints;
    static long[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N][N];
        dp = new long[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                ints[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1L;
            }
        }

        System.out.println(dfs(0, 0));
    }

    public static long dfs(int x, int y) {
        if(x == N - 1 && y == N - 1) return 1;
        if(dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0;
        if(x + ints[x][y] < N) {
            dp[x][y] += dfs(x + ints[x][y], y);
        }

        if(y + ints[x][y] < N) {
            dp[x][y] += dfs(x, y + ints[x][y]);
        }

        return dp[x][y];
    }
}