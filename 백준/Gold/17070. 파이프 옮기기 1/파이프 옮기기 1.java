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
            }
        }

        dp[0][1][0] = 1;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(ints[i][j] == 1) continue;

                if(j > 0) dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][1];
                if(i > 0 && j > 0 && ints[i - 1][j] != 1 && ints[i][j - 1] != 1) dp[i][j][1] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                if(i > 0) dp[i][j][2] += dp[i - 1][j][1] + dp[i - 1][j][2];
            }
        }
        int sum = 0;
        for(int i = 0; i < 3; i++) {
            sum += dp[N - 1][N - 1][i];
        }
        System.out.println(sum);
    }
}