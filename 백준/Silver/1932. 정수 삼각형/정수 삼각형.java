import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] ints = new int[N][];
        int[][] dp = new int[N][];

        for (int i = 0; i < N; i++) {
            ints[i] = new int[i + 1];
            dp[i] = new int[i + 1];
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                ints[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                int temp = 0;
                try { temp = Math.max(temp, dp[i - 1][j]); } catch (Exception e) {}
                try { temp = Math.max(temp, dp[i - 1][j - 1]); } catch (Exception e) {}

                dp[i][j] = temp + ints[i][j];
            }
        }
        int max = -1;
        for (int i = 0; i < N; i++) max = Math.max(max, dp[N - 1][i]);
        System.out.println(max);
    }
}