import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[] p;
    static List<String> list;
    static List<Integer> know;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        know = new ArrayList<>();
        p = new int[N + 1];
        for(int i = 1; i <= N; i++) p[i] = i;
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        for(int i = 0; i < K; i++) know.add(Integer.parseInt(st.nextToken()));
        for(int i = 0; i < know.size() - 1; i++) union(know.get(i), know.get(i + 1));

        for(int i = 0; i < M; i++) {
            String s = br.readLine();
            list.add(s);
            st = new StringTokenizer(s);
            K = Integer.parseInt(st.nextToken());
            if(K <= 1) continue;

            int a = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()){
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
        }
        for(int i = 1; i <= N; i++) find(i);

        int cnt = 0;
        for(int i = 0; i < M; i++) {
            String s = list.get(i);
            st = new StringTokenizer(s);
            boolean flag = true;
            K = Integer.parseInt(st.nextToken());

            for(int j = 0; j < K; j++) {
                int n = Integer.parseInt(st.nextToken());
                for(int k : know) {
                    if(p[n] == p[k]) {
                        flag = false;
                        break;
                    }
                }
            }

            if(flag) cnt++;
        }
        System.out.println(cnt);
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