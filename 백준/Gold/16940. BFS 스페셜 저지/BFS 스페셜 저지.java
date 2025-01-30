import java.io.*;
import java.util.*;

class Main {
    static int N;
    static List<List<Integer>> list;
    static Queue<Integer> q;
    static int[] depth;
    static boolean[] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        depth = new int[N + 1];
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) list.add(new ArrayList<>());

        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(x).add(y);
            list.get(y).add(x);
        }
        bfs();


        String s = br.readLine();
        String[] ss = s.split(" ");
        int[] ans = new int[N];
        for(int i = 0; i < N; i++) ans[i] = Integer.parseInt(ss[i]);
        System.out.println(bfs(ans) ? 1 : 0);
    }

    public static boolean bfs(int[] ans) {
        if(ans[0] != 1) return false;
        visit = new boolean[N + 1];
        int bias = 1;

        q.add(1);
        visit[1] = true;

        while(!q.isEmpty()) {
            int x = q.poll();

            Set<Integer> hs = new HashSet<>();
            for(int nx : list.get(x)) {
                if(visit[nx]) continue;

                visit[nx] = true;
                hs.add(nx);
            }

            for(int i = 0; i < hs.size(); i++) {
                int k = ans[bias + i];
                if(hs.contains(k)) q.add(k);
                else return false;
            }
            bias += hs.size();
        }
        return true;
    }

    public static void bfs() {
        q = new LinkedList<>();
        q.add(1);
        depth[1] = 1;

        while(!q.isEmpty()) {
            int x = q.poll();

            for(int nx : list.get(x)) {
                if(depth[nx] != 0) continue;

                q.add(nx);
                depth[nx] = depth[x] + 1;
            }
        }
    }
}