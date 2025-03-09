import java.io.*;
import java.util.*;

class Main {
    static int N, Q;
    static int[] p;
    static PriorityQueue<Line> pq;
    static Queue<Line> q;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        p = new int[N + 1];
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.x1));
        
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.add(new Line(i, x1, x2, y));
            p[i] = i;
        }

        while(!pq.isEmpty()) bfs(pq.poll());
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(p[x] == p[y]) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs(Line init) {
        q = new LinkedList<>();
        q.add(init);

        while(!q.isEmpty()) {
            Line l = q.poll();
            p[l.idx] = p[init.idx];
            
            while(!pq.isEmpty()) {
                Line nL = pq.peek();
                if(nL.x1 <= l.x2) q.add(pq.poll());
                else break;
            }
        }
    }
}

class Line {
    int idx, x1, x2, y;

    public Line(int idx, int x1, int x2, int y) {
        this.idx = idx;
        this.x1 = x1;
        this.x2 = x2;
        this.y = y;
    }
}