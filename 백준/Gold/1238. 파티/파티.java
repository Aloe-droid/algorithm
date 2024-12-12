import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int max = 1_000_000_000;

        int[][] dp = new int[N + 1][N + 1];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            dp[x][y] = t;
        }

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(dp[i][j] == 0 && i != j) dp[i][j] = max;
            }
        }

        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(i == j) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        int maxTime = -1;
        for(int i = 1; i <= N; i++){
            int time = dp[i][X] + dp[X][i];
            maxTime = Math.max(time, maxTime);
        }

        System.out.println(maxTime);
    }
}