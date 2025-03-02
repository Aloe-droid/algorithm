import java.io.*;
import java.util.*;

class Main {
    static int N, M, C = 4;
    static List<List<Integer>> list;
    static boolean[] visit;
    static boolean flag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        for(int i = 0; i < N; i++) list.add(new ArrayList<>());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(x).add(y);
            list.get(y).add(x);
        }

        flag = false;
        visit = new boolean[N];
        for(int i = 0; i < N; i++) {
            if(flag) break;

            visit[i] = true;
            dfs(i, 0);
            visit[i] = false;
        }

        System.out.println(flag ? 1 : 0);
    }

    public static void dfs(int x, int cnt) {
        if(flag) return;

        for(int nx : list.get(x)) {
            if(visit[nx] || flag) continue;

            visit[nx] = true;
            if(cnt + 1 >= C) flag = true;
            else dfs(nx, cnt + 1);
            visit[nx] = false;
        }
    }

}