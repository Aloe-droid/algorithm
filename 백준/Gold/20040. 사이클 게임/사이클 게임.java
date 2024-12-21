import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[] p;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        p = new int[N];
        for(int i = 0; i < N; i++) p[i] = i;

        int ans = 0;
        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(find(x) == find(y)) {
                ans = i;
                break;
            }

            union(x, y);
        }

        System.out.println(ans);
    }

    public static int find(int x){
        if(p[x] == x) return x;
        return p[x] = find(p[x]);
    }

    public static void union(int a, int b) {
        int pA = find(a);
        int pB = find(b);
        p[pA] = pB;
    }
}