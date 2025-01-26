import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static List<List<Integer>> list;
    static boolean[] visit;
    static int[] cnt;
    static Queue<Integer> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(y).add(x);
        }


        q = new LinkedList<>();
        visit = new boolean[N + 1];
        cnt = new int[N + 1];

        int max = 0;
        for(int i = 1; i <= N; i++) max = Math.max(max, bfs(i));

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if(cnt[i] == max) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    public static int bfs(int k) {
        Arrays.fill(visit, false);

        visit[k] = true;
        q.add(k);
        cnt[k] += 1;

        while (!q.isEmpty()) {
            int x = q.poll();
            for(int nx : list.get(x)) {
                if(visit[nx]) continue;
                visit[nx] = true;
                q.add(nx);
                cnt[k] += 1;
            }
        }

        return cnt[k];
    }
}