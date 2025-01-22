import java.io.*;

class Main {
    static int N;
    static int[] ints, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N];
        dp = new int[N];
        for(int i = 0; i < N; i++) ints[i] = Integer.parseInt(br.readLine());

        dp[0] = ints[0];
        if(N > 1) dp[1] = ints[0] + ints[1];
        if(N > 2) dp[2] = Math.max(Math.max(ints[0] + ints[1], ints[0] + ints[2]), ints[1] + ints[2]);

        for(int i = 3; i < N; i++) {
            int v1 = ints[i] + ints[i - 1] + dp[i - 3];
            int v2 = ints[i] + dp[i - 2];
            int v3 = dp[i - 1];
            dp[i] = Math.max(Math.max(v1, v2), v3);
        }
        System.out.println(dp[N - 1]);
    }
}
