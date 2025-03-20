import java.util.*;

class Solution {
    Node[] nodes;
    Node first, now;
    Stack<Node> dels;
    
    public String solution(int n, int k, String[] cmds) {
        init(n, k);
        for(String cmd : cmds) {
            StringTokenizer st = new StringTokenizer(cmd);
            char op = st.nextToken().charAt(0);
            if(op == 'D') down(Integer.parseInt(st.nextToken()));
            else if(op == 'U') up(Integer.parseInt(st.nextToken()));
            else if(op == 'C') delete();
            else rollBack();
        }
        return build(n);
    }
    
    public void init(int n, int k) {
        dels = new Stack<>();
        first = new Node(-1);
        nodes = new Node[n];
        
        for(int i = 0; i < n; i++) nodes[i] = new Node(i);
        for(int i = 1; i < n - 1; i++) {
            nodes[i].tail = nodes[i + 1];
            nodes[i + 1].head = nodes[i];
            
            nodes[i].head = nodes[i - 1];
            nodes[i - 1].tail = nodes[i];
        }
        
        nodes[n - 1].head = nodes[n - 2];
        nodes[n - 2].tail = nodes[n - 1];
        nodes[0].head = first;
        first.tail = nodes[0];
        
        now = nodes[k];
    }
    
    public void down(int x) {
        for(int i = 0; i < x; i++) now = now.tail;
    }
    
    public void up(int x) {
        for(int i = 0; i < x; i++) now = now.head;
    }
    
    public void delete() {
        dels.add(now);
        Node head = now.head;
        Node tail = now.tail;
        
        head.tail = tail;
        if(tail != null) tail.head = head;
        
        if(tail == null) now = head;
        else now = tail;
    }
    
    public void rollBack() {
        Node n = dels.pop();
        
        Node head = n.head;
        Node tail = n.tail;
        
        head.tail = n;
        if(tail != null) tail.head = n;
    }
    
    public String build(int n) {
        StringBuilder sb = new StringBuilder();
        boolean[] check = new boolean[n];
        
        Node node = first.tail;
        while(node != null) {
            check[node.id] = true;
            node = node.tail;
        }
        
        for(int i = 0; i < n; i++) {
            if(check[i]) sb.append("O");
            else sb.append("X");
        }
        return sb.toString();
    }
}

class Node {
    int id;
    Node head, tail;
    
    public Node(int id) {
        this.id = id;
    }
    
    public String toString() {
        String s = "id : " + id;
        if(head != null) s += ", head : " + head.id;
        if(tail != null) s += ", tail : " + tail.id;
        return s;
    }
}