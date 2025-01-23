import java.io.*;

class Main {
    static int N, dv = 1_000_000;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][2][3];
        System.out.println(dfs(0, 1, 2));
    }

    public static int dfs(int day, int delay, int absent) {
        if (day >= N) return 1;
        if (dp[day][delay][absent] != 0) return dp[day][delay][absent];

        dp[day][delay][absent] = 0;

        // 다음날 출석 하는 경우
        int v1 = dfs(day + 1, delay, 2);
        // 다음날 지각 하는 경우
        int v2 = delay > 0 ? dfs(day + 1, delay - 1, 2) : 0;
        // 다음날 결석 하는 경우
        int v3 = absent > 0 ? dfs(day + 1, delay, absent - 1) : 0;

        dp[day][delay][absent] += v1 % dv;
        dp[day][delay][absent] += v2 % dv;
        dp[day][delay][absent] += v3 % dv;
        return dp[day][delay][absent] %= dv;
    }
}
