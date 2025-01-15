import java.io.*;
import java.util.*;

class Main {
    static int[] ww, ss;
    static int G, L, cnt = 0;
    static String W, S;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        G = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        W = br.readLine();
        S = br.readLine();
        ww = new int['z' - 'A' + 1];
        ss = new int['z' - 'A' + 1];
        for(int i = 0; i < G; i++) {
            int i1 = W.charAt(i) - 'A';
            int i2 = S.charAt(i) - 'A';
            ww[i1]++;
            ss[i2]++;
        }
        check();

        for(int i = G; i < L; i++) {
            int prev = S.charAt(i - G) - 'A';
            int now = S.charAt(i) - 'A';
            ss[prev]--;
            ss[now]++;
            check();
        }

        System.out.println(cnt);
    }

    public static void check() {
        for(int i = 0; i < ss.length; i++) {
            if(ss[i] != ww[i]) return;
        }
        cnt++;
    }

}