import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] ints = new int[N][3];
        int[][][] dp = new int[N][3][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                ints[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0) dp[i][j][0] = ints[i][j];
            }
        }

        int max = 1_000_000_000;
        dp[1][0][0] = max;
        dp[1][0][1] = dp[0][1][0] + ints[1][0];
        dp[1][0][2] = dp[0][2][0] + ints[1][0];

        dp[1][1][0] = dp[0][0][0] + ints[1][1];
        dp[1][1][1] = max;
        dp[1][1][2] = dp[0][2][0] + ints[1][1];

        dp[1][2][0] = dp[0][0][0] + ints[1][2];
        dp[1][2][1] = dp[0][1][0] + ints[1][2];
        dp[1][2][2] = max;

        for (int i = 2; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][0][j] = Math.min(dp[i - 1][1][j], dp[i - 1][2][j]) + ints[i][0];
                dp[i][1][j] = Math.min(dp[i - 1][0][j], dp[i - 1][2][j]) + ints[i][1];
                dp[i][2][j] = Math.min(dp[i - 1][0][j], dp[i - 1][1][j]) + ints[i][2];
            }
        }

        dp[N - 1][0][0] = max;
        dp[N - 1][1][1] = max;
        dp[N - 1][2][2] = max;

        int min = max;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                min = Math.min(min, dp[N - 1][i][j]);
            }
        }

        System.out.println(min);
    }
}