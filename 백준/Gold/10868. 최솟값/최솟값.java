import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[] ints, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ints = new int[N + 1];
        tree = new int[N * 4];

        for(int i = 1; i <= N; i++) ints[i] = Integer.parseInt(br.readLine());
        init(1, 1, N);

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            sb.append(query(1, 1, N, x, y)).append("\n");
        }
        System.out.println(sb);
    }

    public static void init (int node, int left, int right) {
        if(left == right) {
            tree[node] = ints[left];
            return;
        }

        int mid = (left + right) / 2;
        init(node * 2, left, mid);
        init(node * 2 + 1, mid + 1, right);
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
