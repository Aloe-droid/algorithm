import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static Product[] info, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        info = new Product[N + 1];
        dp = new Product[N + 1];
        for(int i = 1; i <= N; i++) info[i] = new Product(N);

        boolean[] check = new boolean[N + 1];
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            check[x] = true;
            info[x].needs[y] += k;
        }

        for(int i = 1; i <= N; i++) {
            if(check[i]) continue;
            dp[i] = new Product(N);
            dp[i].needs[i] = 1;
        }

        Product result = dfs(N);
        for(int i = 1; i <= N; i++) {
            if(result.needs[i] == 0) continue;
            sb.append(i).append(" ").append(result.needs[i]).append("\n");
        }
        System.out.println(sb);
    }

    public static Product dfs(int x) {
        if(dp[x] != null) return dp[x];

        dp[x] = new Product(N);
        int[] needs = info[x].needs;
        for(int i = 1; i <= N; i++) {
            if(needs[i] == 0) continue;
            Product nP = dfs(i);

            for(int j = 1; j <= N; j++) {
                if(nP.needs[j] == 0) continue;
                dp[x].needs[j] += nP.needs[j] * needs[i];
            }
        }

        return dp[x];
    }
}

class Product {
    int[] needs;

    public Product(int n) {
        needs = new int[n + 1];
    }
}
