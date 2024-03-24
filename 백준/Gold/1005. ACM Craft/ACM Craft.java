import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] delays;
    static int[] inDegrees;
    static int[] dp;
    static List<List<Integer>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            delays = new int[N + 1];
            inDegrees = new int[N + 1];
            dp = new int[N + 1];
            list = new ArrayList<>();
            for (int i = 0; i <= N; i++) list.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) delays[i] = Integer.parseInt(st.nextToken());

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.get(x).add(y);
                inDegrees[y]++;
            }
            int end = Integer.parseInt(br.readLine());

            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i <= N; i++) {
                if (inDegrees[i] == 0) {
                    q.add(i);
                    dp[i] = delays[i];
                }
            }

            while (!q.isEmpty()) {
                int x = q.poll();
                for (int nx : list.get(x)) {
                    inDegrees[nx]--;
                    dp[nx] = Math.max(dp[nx], dp[x] + delays[nx]);
                    if (inDegrees[nx] == 0) {
                        q.add(nx);
                    }
                }
            }
            sb.append(dp[end]).append("\n");

        }
        System.out.println(sb);
    }
}