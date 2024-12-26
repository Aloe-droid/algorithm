import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] ints, p;
    static boolean[] check;
    static Queue<Integer> q;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            p = new int[N + 1];
            ints = new int[N + 1];
            check = new boolean[N + 1];
            for(int i = 1; i <= N; i++) p[i] = i;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++) {
                int k = Integer.parseInt(st.nextToken());
                ints[i] = k;
                union(i, k);
            }
            for(int i = 1; i <= N; i++) find(i);
            for(int i = 1; i <= N; i++) {
                if(p[i] == i) bfs(i);
            }

            int cnt = 0;
            for(int i = 1; i <= N; i++) if(!check[i]) cnt++;
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs(int x) {
        q = new LinkedList<>();
        q.add(x);
        check[x] = true;

        while(!q.isEmpty()){
            int nx = ints[q.poll()];
            if(!check[nx]) {
                check[nx] = true;
                q.add(nx);
            }
        }
    }

    public static void union(int x, int y) {
        int pX = find(x);
        int pY = find(y);
        p[pX] = pY;
    }

    public static int find(int k) {
        if(p[k] == k) return k;
        return p[k] = find(p[k]);
    }
}