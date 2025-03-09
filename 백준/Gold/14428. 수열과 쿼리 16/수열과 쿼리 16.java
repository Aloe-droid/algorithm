import java.io.*;
import java.util.*;
class Main {
    static int N, M;
    static Node[] nodes, tree;

    static Node trash = new Node(-1, Integer.MAX_VALUE);
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N + 1];
        tree = new Node[N * 4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            int v = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(i, v);
        }
        init(1, 1, N);

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(op == 1) update(1, 1, N, a, b);
            else sb.append(query(1, 1, N, a, b).i).append("\n");
        }
        System.out.println(sb);
    }

    public static void init(int node, int left, int right) {
        if(left == right) {
            tree[node] = nodes[left];
            return;
        }

        int mid = (left + right) / 2;
        init(node * 2, left, mid);
        init(node * 2 + 1, mid + 1, right);
        tree[node] = min(tree[node * 2], tree[node * 2 + 1]);
    }

    public static void update(int node, int left, int right, int idx, int value) {
        if(idx < left || idx > right) return;
        if(left == right) {
            tree[node].v = value;
            return;
        }

        int mid = (left + right) / 2;
        update(node * 2, left, mid, idx, value);
        update(node * 2 + 1, mid + 1, right, idx, value);
        tree[node] = min(tree[node * 2], tree[node * 2 + 1]);
    }

    public static Node query(int node, int left, int right, int i, int j) {
        if(i > right || j < left) return trash;
        if(i <= left && j >= right) return tree[node];

        int mid = (left + right) / 2;
        Node n1 = query(node * 2, left, mid, i, j);
        Node n2 = query(node * 2 + 1, mid + 1, right, i, j);
        return min(n1, n2);
    }

    public static Node min(Node n1, Node n2) {
        if(n1.v == n2.v) {
            if(n1.i < n2.i) return n1;
            else return n2;
        }

        if(n1.v < n2.v) return n1;
        else return n2;
    }
}

class Node {
    int i, v;
    public Node(int i, int v) {
        this.i = i;
        this.v = v;
    }

}
