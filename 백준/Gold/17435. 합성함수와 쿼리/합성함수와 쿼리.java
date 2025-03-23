import java.io.*;
import java.util.*;

class Main {
    static int N, Q;
    static int[][] table;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        table = new int[N + 1][21];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) table[i][0] = Integer.parseInt(st.nextToken());
        init();

        Q = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            sb.append(query(n, x)).append("\n");
        }
        System.out.println(sb);
    }

    public static void init() {
        for(int i = 1; i < 21; i++) {
            for(int j = 1; j <= N; j++) {
                table[j][i] = table[table[j][i - 1]][i - 1];
            }
        }
    }

    public static int query(int n, int x) {
        while (n > 0) {
            int k = 0;
            while ((int) Math.pow(2, k) <= n) k++;
            k--;

            x = table[x][k];
            n -= (int) Math.pow(2, k);
        }
        return x;
    }
}
