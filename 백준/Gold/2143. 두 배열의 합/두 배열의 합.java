import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long T = Long.parseLong(br.readLine());

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] a = new long[N + 1];
        for(int i = 1; i <= N; i++) a[i] = Long.parseLong(st.nextToken()) + a[i - 1];

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        long[] b = new long[M + 1];
        for(int i = 1; i <= M; i++) b[i] = Long.parseLong(st.nextToken()) + b[i - 1];

        Map<Long, Long> hm = new HashMap<>();
        for(int i = 0; i <= N - 1; i++){
            for(int j = i + 1; j <= N; j++){
                long k = a[j] - a[i];
                hm.put(k, hm.getOrDefault(k, 0L) + 1);
            }
        }

        long cnt = 0;
        for(int i = 0; i <= M - 1; i++){
            for(int j = i + 1; j <= M; j++){
                long k = T - (b[j] - b[i]);
                cnt += hm.getOrDefault(k, 0L);
            }
        }

        System.out.println(cnt);
    }
}