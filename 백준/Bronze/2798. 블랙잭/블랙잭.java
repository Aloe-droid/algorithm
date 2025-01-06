import java.io.*;
import java.util.*;
class Main {
    static int N, M, ans = 0;
    static int[] ints;
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ints = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) ints[i] = Integer.parseInt(st.nextToken());

        comb(0, 0, new int[3]);
        System.out.println(ans);
    }

    public static void comb(int k, int start, int[] index){
        if(k == 3) {
            int sum = ints[index[0]] + ints[index[1]] + ints[index[2]];
            if(sum <= M) ans = Math.max(ans, sum);
            return;
        }

        for(int i = start; i < N; i++) {
            index[k] = i;
            comb(k + 1, i + 1, index);
        }
    }
}