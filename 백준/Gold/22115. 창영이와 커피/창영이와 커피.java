import java.io.*;
import java.util.*;

class Main {
    static int N, K, max = 100_000_000;
    static int[] ints;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ints = new int[N];
        dp = new int[N][100_001];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], max);
        }

        dp[0][ints[0]] = 1;
        for(int i = 1; i < N; i++) {
            int n = ints[i];
            dp[i][n] = 1;

            for(int j = 0; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
            }

            for(int j = n; j <= K; j++) {
                int temp = Math.min(dp[i - 1][j], dp[i - 1][j - n] + 1);
                dp[i][j] = Math.min(dp[i][j], temp);
            }
        }

        if(K == 0) System.out.println(0);
        else System.out.println(dp[N - 1][K] == max ? -1 : dp[N - 1][K]);
    }
}