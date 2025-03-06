import java.io.*;
import java.util.*;

class Main {
    static int N, K, max = 1_000_000_000;
    static int[][] ints, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) ints[i][j] = Integer.parseInt(st.nextToken());
        }

        K = (int) Math.pow(2, N);
        dp = new int[N][K];
        for(int i = 0; i < N; i++) Arrays.fill(dp[i], -1);

        System.out.println(dfs(0, 0));
    }

    public static int dfs(int x, int bit) {
        if(x >= N) return 0;
        if(dp[x][bit] != -1) return dp[x][bit];

        dp[x][bit] = max;
        for(int i = 0; i < N; i++) {
            // i번째 일을 이미 수행한 경우
            if((bit & (1 << i)) != 0) continue;

            // i 번째 일을 수행하는 경우
            int nextBit = bit | (1 << i);
            int value = dfs(x + 1, nextBit) + ints[x][i];
            dp[x][bit] = Math.min(dp[x][bit], value);
        }

        return dp[x][bit];
    }
}