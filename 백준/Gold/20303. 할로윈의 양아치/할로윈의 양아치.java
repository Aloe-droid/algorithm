import java.io.*;
import java.util.*;

class Main {
    static int N, M, K;
    static int[] p, c;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        c = new int[N + 1];
        p = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
            p[i] = i;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x, y);
        }

        Set<Integer> hs = new HashSet<>();
        for(int i = 1; i <= N; i++) hs.add(find(i));

        int[][] ints = new int[hs.size()][2];
        int idx = 0;
        for(int k : hs) {
            for(int i = 1; i <= N; i++) {
                if(p[i] == k) {
                    ints[idx][0] += 1;
                    ints[idx][1] += c[i];
                }
            }
            idx++;
        }

        int[] dp = new int[K + 1];
        for (int[] anInt : ints) {
            for (int j = K; j >= anInt[0]; j--) {
                dp[j] = Math.max(dp[j], dp[j - anInt[0]] + anInt[1]);
            }
        }

        System.out.println(dp[K - 1]);
    }

    public static int find(int k){
        if(p[k] == k) return k;
        return p[k] = find(p[k]);
    }

    public static void union(int x, int y) {
        int pX = find(x);
        int pY = find(y);
        p[pX] = pY;
    }
}