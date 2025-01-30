import java.io.*;

class Main {
    static long[] dp = new long[101];
    public static void main(String[] args) throws Exception {
        find();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }

    public static void find() {
        for(int i = 1; i <= 3; i++) dp[i] = 1;
        for(int i = 4; i <= 5; i++) dp[i] = 2;
        for(int i = 5; i <= 100; i++) dp[i] = dp[i - 1] + dp[i - 5];
    }
}