import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[] ints, tree;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        ints = new int[N + 1];
        tree = new int[N * 4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) ints[i] = Integer.parseInt(st.nextToken());
        init(1, 1, N);

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(op == 1) update(1, 1, N, a, b);
            else sb.append(query(1, 1, N, a, b)).append("\n");
        }
        System.out.println(sb);
    }

    public static void init(int node, int left, int right) {
        if(left == right) {
            tree[node] = ints[left];
            return;
        }

        int mid = (left + right) / 2;
        init(node * 2, left, mid);
        init(node * 2 + 1, mid + 1, right);
        tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }


    public static void update(int node, int left, int right, int idx, int value) {
        if(idx < left || idx > right) return;
        if(left == right) {
            tree[node] = value;
            return;
        }

        int mid = (left + right) / 2;
        update(node * 2, left, mid, idx, value);
        update(node * 2 + 1, mid + 1, right, idx, value);
        tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }

    public static int query(int node, int left, int right, int i, int j) {
        if(i > right || j < left) return Integer.MAX_VALUE;
        if(i <= left && j >= right) return tree[node];

        int mid = (left + right) / 2;
        int v1 = query(node * 2, left, mid, i, j);
        int v2 = query(node * 2 + 1, mid + 1, right, i, j);
        return Math.min(v1, v2);
    }

}
