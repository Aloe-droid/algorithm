import java.io.*;
import java.util.*;

class Main {
    static int N, K, len = 500_000;
    static int[][] dp;
    static Queue<Integer> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[len + 1][2];
        for (int[] d : dp) Arrays.fill(d, 100_000_000);
        find();
        
        int c = 0;
        boolean flag = false;
        while(K <= len) {
            if(dp[K][0] <= c && dp[K][0] % 2 == c % 2) {
                flag = true;
                break;
            }

            if(dp[K][1] <= c && dp[K][1] % 2 == c % 2) {
                flag = true;
                break;
            }

            K += ++c;
        }

        System.out.println(flag ? c : -1);
    }

    // 최소로 이동할 수 있는 경우 확인 (짝수, 홀수 구분)
    public static void find() {
        q = new LinkedList<>();
        q.add(N);
        dp[N][0] = 0;

        while(!q.isEmpty()) {
            int x = q.poll();

            if(check(x, x - 1)) {
                q.add(x - 1);
            }

            if(check(x, x + 1)) {
                q.add(x + 1);
            }

            if(check (x, 2 * x)) {
                q.add(2 * x);
            }
        }
    }

    public static boolean check(int prev, int now){
        if(now < 0 || now > len) return false;

        boolean flag = false;
        if(dp[prev][0] != -1 && dp[now][1] > dp[prev][0] + 1) {
            dp[now][1] = dp[prev][0] + 1;
            flag = true;
        }

        if(dp[prev][1] != -1 && dp[now][0] > dp[prev][1] + 1) {
            dp[now][0] = dp[prev][1] + 1;
            flag = true;
        }

        return flag;
    }
}