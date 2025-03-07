import java.io.*;
import java.util.*;

class Main {
    static int N, K, max = 200_000;
    static Queue<Integer> q;
    static int[] visit, count;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visit = new int[max + 1];
        count = new int[max + 1];
        q = new LinkedList<>();

        bfs();
        System.out.println((visit[K] - 1) + "\n" + count[K]);
    }

    public static void bfs() {
        q.add(N);
        visit[N] = 1;
        count[N] = 1;

        while(!q.isEmpty()) {
            int x = q.poll();

            put(x, x + 1);
            put(x, x - 1);
            put(x, 2 * x);
        }
    }

    public static void put(int x, int nx) {
        if(nx < 0 || nx > max) return;
        if(visit[nx] != 0) {
            if(visit[nx] == visit[x] + 1) count[nx] += count[x];
            return;
        }

        visit[nx] = visit[x] + 1;
        count[nx] = count[x];
        q.add(nx);
    }
}