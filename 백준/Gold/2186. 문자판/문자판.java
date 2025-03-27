import java.io.*;
import java.util.*;

class Main {
    static int N, M, K;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static char[][] chars;
    static String ans;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        chars = new char[N][M];
        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                chars[i][j] = s.charAt(j);
            }
        }
        ans = br.readLine();

        dp = new int[N][M][ans.length()];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        
        int sum = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(chars[i][j] != ans.charAt(0)) continue;
                sum += dfs(i, j, 1);
            }
        }
        System.out.println(sum);
    }

    public static int dfs(int x, int y, int id) {
        if(id >= ans.length()) return 1;
        if(dp[x][y][id] != -1) return dp[x][y][id];

        dp[x][y][id] = 0;
        char c = ans.charAt(id);
        for(int k = 1; k <= K; k++) {
            for(int n = 0; n < 4; n++) {
                int nx = x + dx[n] * k;
                int ny = y + dy[n] * k;
                if(nx < 0 || nx >= N || ny < 0 || ny >= M || chars[nx][ny] != c) continue;
                dp[x][y][id] += dfs(nx, ny, id + 1);
            }
        }
        return dp[x][y][id];
    }
}
