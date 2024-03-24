import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] ints;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                ints[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.arraycopy(ints[0], 0, dp[0], 0, 3);

        dfs(N - 1, 0);
        dfs(N - 1, 1);
        dfs(N - 1, 2);

        int min = Math.min(dp[N - 1][0], dp[N - 1][1]);
        min = Math.min(min, dp[N - 1][2]);
        System.out.println(min);
    }

    public static int dfs(int idx, int color) {
        if (dp[idx][color] != 0) return dp[idx][color];

        if (color == 0) {
            dp[idx][0] = Math.min(dfs(idx - 1, 1), dfs(idx - 1, 2)) + ints[idx][0];
        } else if (color == 1) {
            dp[idx][1] = Math.min(dfs(idx - 1, 0), dfs(idx - 1, 2)) + ints[idx][1];
        } else {
            dp[idx][2] = Math.min(dfs(idx - 1, 0), dfs(idx - 1, 1)) + ints[idx][2];
        }
        return dp[idx][color];
    }
}