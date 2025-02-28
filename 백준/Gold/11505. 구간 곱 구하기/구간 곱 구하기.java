import java.io.*;
import java.util.*;

class Main {
    static int N, M, K, dv = 1_000_000_007;
    static long[] longs, tree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        longs = new long[N + 1];
        tree = new long[N * 4];
        for(int i = 1; i <= N; i++) longs[i] = Long.parseLong(br.readLine());
        init(1, 1, N);

        for(int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(op == 1) update(1, 1, N, x, y);
            else sb.append(query(1, 1, N, x, y)).append("\n");
        }
        System.out.println(sb);
    }

    public static void init(int node, int left, int right) {
        if(left == right) {
            tree[node] = longs[left];
            return;
        }

        int mid = (left + right) / 2;
        init(node * 2, left, mid);
        init(node * 2 + 1, mid + 1, right);
        tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % dv;
    }

    public static void update(int node, int left, int right, int id, long value) {
        if(id < left || id > right) return;
        if(left == right) {
            tree[node] = value;
            return;
        }

        int mid = (left + right) / 2;
        update(node * 2, left, mid, id, value);
        update(node * 2 + 1, mid + 1, right, id, value);
        tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % dv;
    }

    public static long query(int node, int left, int right, int i, int j) {
        if(i > right || j < left) return 1;
        if(i <= left && j >= right) return tree[node];

        int mid = (left + right) / 2;
        long v1 = query(node * 2, left, mid, i, j);
        long v2 = query(node * 2 + 1, mid + 1, right, i, j);
        return (v1 * v2) % dv;
    }
}
