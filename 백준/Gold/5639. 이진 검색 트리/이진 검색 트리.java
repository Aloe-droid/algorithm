import java.io.*;

class Main {
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        Node node = new Node(Integer.parseInt(br.readLine()));

        while(true){
            String s = br.readLine();
            if(s == null || s.isEmpty()) break;
            Node n = new Node(Integer.parseInt(s));
            setNode(node, n);
        }

        dfs(node);
        System.out.println(sb);
    }

    public static void dfs(Node n) {
        if(n.left != null) dfs(n.left);
        if(n.right != null) dfs(n. right);
        sb.append(n.value).append("\n");
    }


    public static void setNode(Node p, Node n){
        if(p.value > n.value) {
            if(p.left == null) p.left = n;
            else setNode(p.left, n);
        }else if(p.value < n.value) {
            if(p.right == null) p.right = n;
            else setNode(p.right, n);
        }
    }
}

class Node {
    int value;
    Node left, right;
    public Node(int value) {
        this.value = value;
    }
}