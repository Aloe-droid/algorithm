import java.io.*;
import java.util.*;

class Main {
    static int N, M, K;
    static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1}, dy = {0, 0, 1, -1, 1, -1, 1, -1};
    static char[][] chars;
    static int[][][] dp;
    static String ss;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        chars = new char[N][M];
        dp = new int[N][M][K];

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                chars[i][j] = s.charAt(j);
            }
        }

        for(int i = 0; i < K; i++) {
            ss = br.readLine();
            initDP();
            sb.append(sum()).append("\n");
        }
        System.out.println(sb);
    }

    public static int dfs(int x, int y, int k) {
        if(k == ss.length()) return 1;
        if(dp[x][y][k] != -1) return dp[x][y][k];

        dp[x][y][k] = 0;
        for(int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx == -1) nx = N - 1;
            else if(nx == N) nx = 0;
            if(ny == -1) ny = M - 1;
            else if(ny == M) ny = 0;

            if(ss.charAt(k) != chars[nx][ny]) continue;
            dp[x][y][k] += dfs(nx, ny, k + 1);
        }
        return dp[x][y][k];
    }

    public static int sum() {
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(chars[i][j] != ss.charAt(0)) continue;
                cnt += dfs(i, j, 1);
            }
        }
        return cnt;
    }

    public static void initDP() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                for(int k = 0; k < K; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
    }
}
