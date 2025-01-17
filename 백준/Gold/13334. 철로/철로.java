import java.io.*;
import java.util.*;

class Main {
    static int N, L;
    static K[] ks;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ks = new K[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());
            int h = Math.min(t1, t2);
            int o = Math.max(t1, t2);
            ks[i] = new K(h, o);
        }
        L = Integer.parseInt(br.readLine());
        Arrays.sort(ks, Comparator.comparingInt(k -> k.o));

        int max = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            pq.add(ks[i].h);
            while(!pq.isEmpty() && pq.peek() < ks[i].o - L) pq.poll();
            max = Math.max(max, pq.size());
        }
        System.out.println(max);
    }
}

class K {
    int h, o;

    public K(int h, int o) {
        this.h = h;
        this.o = o;
    }
}