import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) parents[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op == 0) {
                union(a, b);
            } else {
                sb.append(find(a) == find(b) ? "YES\n" : "NO\n");
            }
        }
        System.out.println(sb);
    }

    public static void union(int x, int y) {
        int pX = find(x);
        int pY = find(y);
        parents[pX] = pY;
    }

    public static int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }
}