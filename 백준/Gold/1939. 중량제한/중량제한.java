import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<List<Bridge>> list;
    static Queue<Integer> q;
    static boolean[] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.get(a).add(new Bridge(b, c));
            list.get(b).add(new Bridge(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int left = 1;
        int right = 1_000_000_000;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (bfs(x, y, mid)) left = mid + 1;
            else right = mid - 1;
        }
        System.out.println(right);
    }

    public static boolean bfs(int x, int y, int w) {
        q = new LinkedList<>();
        check = new boolean[N + 1];

        q.add(x);
        check[x] = true;
        while (!q.isEmpty()) {
            x = q.poll();
            for (Bridge bridge : list.get(x)) {
                if (bridge.weight < w || check[bridge.node]) continue;
                check[bridge.node] = true;
                q.add(bridge.node);
            }
        }
        return check[y];
    }
}

class Bridge {
    int node;
    int weight;

    Bridge(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}
