import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static Node[] nodes;
    static boolean[] check;
    static StringBuilder[] sbArray;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nodes = new Node[N];
        check = new boolean[N];
        sbArray = new StringBuilder[3];

        for (int i = 0; i < 3; i++) sbArray[i] = new StringBuilder();
        for (int i = 0; i < N; i++) nodes[i] = new Node(i);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = st.nextToken().charAt(0) - 'A';
            int b = st.nextToken().charAt(0) - 'A';
            int c = st.nextToken().charAt(0) - 'A';
            Node n = nodes[a];
            if (b != '.' - 'A') n.left = nodes[b];
            if (c != '.' - 'A') n.right = nodes[c];
        }

        check[0] = true;
        dfs(nodes[0]);

        for (int i = 0; i < 3; i++) {
            System.out.println(sbArray[i]);
        }
    }

    public static void dfs(Node node) {
        sbArray[0].append(node.toChar());

        if (node.left != null && !check[node.left.idx]) {
            check[node.left.idx] = true;
            dfs(node.left);
        }

        sbArray[1].append(node.toChar());

        if (node.right != null && !check[node.right.idx]) {
            check[node.right.idx] = true;
            dfs(node.right);
        }

        sbArray[2].append(node.toChar());
    }
}

class Node {
    int idx;
    Node left, right;

    public Node(int idx) {
        this.idx = idx;
    }

    public char toChar() {
        return (char) (idx + 'A');
    }
}