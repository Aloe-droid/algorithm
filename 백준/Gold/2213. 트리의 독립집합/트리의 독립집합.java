import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;
    static int[] ints;
    static List<List<Integer>> list;
    static List<List<Set<int[]>>> hs;
    static int[][] dp;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) ints[i] = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(x).add(y);
            list.get(y).add(x);
        }

        dp = new int[N + 1][2];
        visit = new boolean[N + 1];
        hs = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            List<Set<int[]>> sets = new ArrayList<>();
            for (int j = 0; j < 2; j++) sets.add(new HashSet<>());
            hs.add(sets);
        }
        dfs(1);

        int max;
        String s;
        if (dp[1][0] > dp[1][1]) {
            max = dp[1][0];
            s = bfs(new int[]{1, 0});
        } else {
            max = dp[1][1];
            s = bfs(new int[]{1, 1});
        }

        System.out.println(max);
        System.out.println(s);
    }

    public static void dfs(int x) {
        visit[x] = true;
        dp[x][0] = 0;
        dp[x][1] = ints[x];

        for (int nx : list.get(x)) {
            if (visit[nx]) continue;

            dfs(nx);

            if (dp[nx][1] > dp[nx][0]) {
                dp[x][0] += dp[nx][1];
                hs.get(x).get(0).add(new int[]{nx, 1});
            } else {
                dp[x][0] += dp[nx][0];
                hs.get(x).get(0).add(new int[]{nx, 0});
            }

            dp[x][1] += dp[nx][0];
            hs.get(x).get(1).add(new int[]{nx, 0});
        }
    }

    public static String bfs(int[] init) {
        List<Integer> edge = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[N + 1][2];

        q.add(init);
        visit[init[0]][init[1]] = true;

        while (!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.poll()[1];
            if (y == 1) edge.add(x);

            for (int[] next : hs.get(x).get(y)) {
                int nx = next[0];
                int ny = next[1];
                if (visit[nx][ny]) continue;

                q.add(new int[]{nx, ny});
                visit[nx][ny] = true;
            }
        }

        Collections.sort(edge);
        StringBuilder sb = new StringBuilder();
        for (int e : edge) sb.append(e).append(" ");
        return sb.toString();
    }
}