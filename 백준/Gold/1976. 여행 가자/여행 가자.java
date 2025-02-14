import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[] p;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        p = new int[N + 1];
        for(int i = 0; i <= N; i++) p[i] = i;

        for(int i = 1; i <= N; i++) {
            String[] ss = br.readLine().split(" ");
            for(int j = 1; j <= N; j++) {
                if(i == j) continue;
                int k = Integer.parseInt(ss[j - 1]);
                if(k == 0) continue;
                union(i, j);
            }
        }

        boolean flag = true;
        String[] ss = br.readLine().split(" ");
        int prevP = find(Integer.parseInt(ss[0]));
        for(int i = 1; i < M; i++) {
            if(prevP != find(Integer.parseInt(ss[i]))) {
                flag = false;
                break;
            }
        }

        System.out.println(flag ? "YES" : "NO");
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