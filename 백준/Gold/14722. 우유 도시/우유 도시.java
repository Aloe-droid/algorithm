import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] ints;
    static int[][][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N][N];
        dp = new int[N][N][3];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                ints[i][j] = Integer.parseInt(st.nextToken());
                if(ints[i][j] == 0) dp[i][j][0] = 1;
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < 3; k++) {
                    int v1 = j - 1 >= 0 ? dp[i][j - 1][k] : 0;
                    int v2 = i - 1 >= 0 ? dp[i - 1][j][k] : 0;
                    dp[i][j][k] = Math.max(Math.max(v1, v2), dp[i][j][k]);
                }
                int k1 = ints[i][j];
                int k2 = k1 > 0 ? k1 - 1 : 2;
                if(dp[i][j][k2] > 0) dp[i][j][k1] = Math.max(dp[i][j][k1], dp[i][j][k2] + 1);
            }
        }

        int max = 0;
        for(int i = 0; i < 3; i++) max = Math.max(max, dp[N - 1][N - 1][i]);
        System.out.println(max);
    }
}