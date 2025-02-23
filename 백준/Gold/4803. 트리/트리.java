import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[] p;
    static boolean[] bools;
    static Set<Integer> hs;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = 1;
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0) break;

            p = new int[N + 1];
            bools = new boolean[N + 1];
            for(int i = 1; i <= N; i++) {
                p[i] = i;
                bools[i] = true;
            }

            for(int i = 0 ; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                union(x, y);
            }

            hs = new HashSet<>();
            for(int i = 1; i <= N; i++) {
                int p = find(i);
                if(bools[p]) hs.add(p);
            }

            String s;
            if(hs.size() == 0) s = "No trees.\n";
            else if(hs.size() == 1) s = "There is one tree.\n";
            else s = "A forest of " + hs.size() + " trees.\n";
            
            sb.append("Case ").append(T).append(": ").append(s);
            T++;
        }
        System.out.println(sb);
    }

    public static void union(int x, int y) {
        int pX = find(x);
        int pY = find(y);
        p[pX] = pY;

        if(bools[pX] == false || bools[pY] == false || pX == pY) {
            bools[pX] = false;
            bools[pY] = false;
        }
    }

    public static int find(int k) {
        if(p[k] == k) return k;
        return p[k] = find(p[k]);
    }
}