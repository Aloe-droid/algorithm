import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] dp;
    static boolean[][] remove;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][N];
        remove = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag = true;
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(i == j || j == k || i == k) continue;
                    int i1 = dp[i][j];
                    int i2 = dp[i][k] + dp[k][j];

                    if(i1 > i2) flag = false;
                    else if(i1 == i2) remove[i][j] = true;
                }
            }
        }

        int sum = 0;
        for(int i = 0; i < N - 1; i++) {
            for(int j = i + 1; j < N; j++) {
                if(!remove[i][j]) sum += dp[i][j];
            }
        }
        System.out.println(flag ? sum : -1);
    }
}
