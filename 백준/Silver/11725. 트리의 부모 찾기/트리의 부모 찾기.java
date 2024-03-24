import java.io.*;
import java.util.*;

class Main {
    static int N;
    static List<List<Integer>> list;
    static int[][] dp;
    static int[] depth;
    static Queue<Integer> q;
    static boolean[] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][18];
        depth = new int[N + 1];
        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(x).add(y);
            list.get(y).add(x);
        }
        bfs();

        for (int i = 2; i <= N; i++) bw.write(dp[i][0] + "\n");
        bw.flush();
    }

    public static void bfs() {
        q = new LinkedList<>();
        check = new boolean[N + 1];
        q.add(1);
        check[1] = true;

        while (!q.isEmpty()) {
            int x = q.poll();

            for (int nx : list.get(x)) {
                if (check[nx]) continue;

                check[nx] = true;
                depth[nx] = depth[x] + 1;
                dp[nx][0] = x;
                q.add(nx);
            }
        }
    }
}
