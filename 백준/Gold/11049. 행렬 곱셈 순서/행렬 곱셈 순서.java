import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] ints, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N][2];
        dp = new int[N][N];
        for(int i = 0; i < N; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ints[i][0] = Integer.parseInt(st.nextToken());
            ints[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dfs(0, N - 1));
    }

    public static int dfs(int x, int y) {
        if(x == y) return 0;
        if(dp[x][y] != Integer.MAX_VALUE) return dp[x][y];

        for(int i = x; i < y ; i++) {
            int value = dfs(x, i) + dfs(i + 1, y) + (ints[x][0] * ints[i][1] * ints[y][1]);
            dp[x][y] = Math.min(dp[x][y], value);
        }
        return dp[x][y];
    }
}