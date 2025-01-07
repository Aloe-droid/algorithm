import java.io.*;
import java.util.*;

class Main {
    static int N, M, min = Integer.MAX_VALUE;
    static List<int[]> house, store;
    static Set<Integer> hs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        house = new ArrayList<>();
        store = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int k = Integer.parseInt(st.nextToken());

                if(k == 1) house.add(new int[] {i, j});
                else if(k == 2) store.add(new int[] {i, j});
            }
        }

        hs = new HashSet<>();
        dfs(0);
        System.out.println(min);
    }

    public static void dfs(int k) {
        if(k > store.size()) return;
        if(hs.size() != 0 && hs.size() <= M) check();

        hs.add(k);
        dfs(k + 1);
        hs.remove(k);
        dfs(k + 1);
    }

    public static void check() {
        int value = 0;

        for(int[] h : house) {
            int dst = Integer.MAX_VALUE;
            for(int k = 0; k < store.size(); k++) {
                if(!hs.contains(k)) continue;
                dst = Math.min(dst, Math.abs(h[0] - store.get(k)[0]) + Math.abs(h[1] - store.get(k)[1]));
            }
            value += dst;
        }

        min = Math.min(min, value);
    }
}