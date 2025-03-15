import java.io.*;
import java.util.*;

class Main {
    static int N;
    static Set<Long> hs;
    static long[] longs, values;
    static boolean flag;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        hs = new HashSet<>();
        longs = new long[N];
        values = new long[N];
        flag = false;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            long value = Long.parseLong(st.nextToken());
            hs.add(value);
            values[i] = value;
        }
        
        for(long value : values) {
            if(flag) break;
            longs[0] = value;
            hs.remove(value);
            dfs(1);
            hs.add(value);
        }

        for(long l : longs) sb.append(l).append(" ");
        System.out.println(sb);
    }

    public static void dfs(int idx) {
        if(flag) return;
        if(idx == N) {
            flag = true;
            return;
        }

        long prev = longs[idx - 1];
        if(!flag && prev % 3 == 0 && hs.contains(prev / 3)) {
            hs.remove(prev / 3);
            longs[idx] = prev / 3;
            dfs(idx + 1);
            hs.add(prev / 3);
        }

        if(!flag && hs.contains(prev * 2)) {
            hs.remove(prev * 2);
            longs[idx] = prev * 2;
            dfs(idx + 1);
            hs.add(prev * 2);
        }
    }
}
