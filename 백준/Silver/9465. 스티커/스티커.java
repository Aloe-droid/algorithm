import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            int N = Integer.parseInt(br.readLine());
            int[][] ints = new int[2][N];
            int[][] dp = new int[2][N];

            for(int i = 0; i < 2; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    ints[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][0] = ints[0][0];
            dp[1][0] = ints[1][0];

            for(int i = 1; i < N; i++){
                int m1 = dp[1][i - 1];
                int m2 = dp[0][i - 1];

                if(i - 2 >= 0) m1 = Math.max(m1, dp[1][i - 2]);
                if(i - 2 >= 0) m2 = Math.max(m2, dp[0][i - 2]);

                dp[0][i] = m1 + ints[0][i];
                dp[1][i] = m2 + ints[1][i];
            }

            sb.append(Math.max(dp[0][N - 1], dp[1][N - 1])).append("\n");
        }
        System.out.println(sb);
    }
}