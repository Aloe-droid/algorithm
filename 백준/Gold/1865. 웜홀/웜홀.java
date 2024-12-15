import java.io.*;
import java.util.*;

class Main {
    static int max = 1_000_000_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= TC; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            int[][] dp = new int[N + 1][N + 1];
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    dp[i][j] = max;
                    if(i == j) dp[i][j] = 0;
                }
            }

            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                if(dp[s][e] > t) dp[s][e] = t;
                if(dp[e][s] > t) dp[e][s] = t;
            }

            for(int i = 0; i < W; i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken()) * -1;
                if(dp[s][e] > t) dp[s][e] = t;
            }

            for(int k = 1; k <= N; k++){
                for(int i = 1; i <= N; i++){
                    for(int j = 1; j <= N; j++){
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }

            boolean flag = false;
            for(int i = 1; i <= N; i++){
                if(dp[i][i] < 0){
                    flag = true;
                    break;
                }
            }

            sb.append(flag ? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }
}