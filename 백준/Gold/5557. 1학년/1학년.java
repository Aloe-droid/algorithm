import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] ints;
    static long[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N];
        dp = new long[N][21];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, ints[0]));
    }

    public static long dfs(int n, int v) {
        if(n == N - 2) return v == ints[N - 1] ? 1 : 0;
        if(dp[n][v] != -1) return dp[n][v];

        dp[n][v] = 0;
        int v1 = v + ints[n + 1];
        int v2 = v - ints[n + 1];

        if(v1 >= 0 && v1 <= 20) dp[n][v] += dfs(n + 1, v1);
        if(v2 >= 0 && v2 <= 20) dp[n][v] += dfs(n + 1, v2);

        return dp[n][v];
    }
}