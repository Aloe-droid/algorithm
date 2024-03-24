import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ints = new int[N + 5];
        int[] dp = new int[N + 5];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        int max = 1;
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                if (ints[j] < ints[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}
