import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static long[] longs, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        longs = new long[N + 1];
        tree = new long[N * 4];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) longs[i] = Long.parseLong(st.nextToken());
        init(1, 1, N);

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int min = Math.min(x, y);
            int max = Math.max(x, y);
            long value = query(1, 1, N, min, max);

            sb.append(value).append("\n");
            update(1, 1, N, a, b);
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
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
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
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public static long query(int node, int left, int right, int i, int j) {
        if(i > right || j < left) return 0L;
        if(i <= left && j >= right) return tree[node];

        int mid = (left + right) / 2;
        long v1 = query(node * 2, left, mid, i, j);
        long v2 = query(node * 2 + 1, mid + 1, right, i, j);
        return v1 + v2;
    }

}
