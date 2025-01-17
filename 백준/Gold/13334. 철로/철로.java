import java.io.*;
import java.util.*;

class Main {
    static int N, L;
    static K[] kH, kO;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        kH = new K[N];
        kO = new K[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());
            int h = Math.min(t1, t2);
            int o = Math.max(t1, t2);
            kH[i] = new K(i, h);
            kO[i] = new K(i, o);
        }
        L = Integer.parseInt(br.readLine());
        Arrays.sort(kH, Comparator.comparingInt(k -> k.v));
        Arrays.sort(kO, Comparator.comparingInt(k -> k.v));

        int max = 0, cnt = 0;
        int[] ints = new int[N];

        int h1 = 0, h2 = 0;
        int o1 = 0, o2 = 0;
        int index = 0;
        while(index < N) {
            int st = kH[index].v, ed = st + L;

            while(h1 < h2 && kH[h1].v < st) {
                if(ints[kH[h1].i] == 2) cnt -= 1;
                ints[kH[h1].i] -= 1;
                h1 += 1;
            }

            while(h2 < N && kH[h2].v <= ed) {
                ints[kH[h2].i] += 1;
                if(ints[kH[h2].i] == 2) cnt += 1;
                h2 += 1;
            }

            while(o1 < o2 && kO[o1].v < st) {
                if(ints[kO[o1].i] == 2) cnt -= 1;
                ints[kO[o1].i] -= 1;
                o1 += 1;
            }

            while(o2 < N && kO[o2].v <= ed) {
                ints[kO[o2].i] += 1;
                if(ints[kO[o2].i] == 2) cnt += 1;
                o2 += 1;
            }

            if(cnt > 0) max = Math.max(max, cnt);
            while(index < N && kH[index].v == st) index++;
        }

        System.out.println(max);
    }
}

class K {
    int i, v;

    public K(int i, int v) {
        this.i = i;
        this.v = v;
    }

    @Override
    public String toString() {
        return "id: " + i  + ", value: " + v;
    }
}