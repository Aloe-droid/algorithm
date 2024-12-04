import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Integer, Integer> hm;
    static List<List<Integer>> list = new ArrayList<>();
    static int[] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        hm = new HashMap<>();
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            hm.put(a, b);
        }

        init();
        bfs();
        System.out.println(check[100]);
    }

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        check[1] = 0;

        while (!q.isEmpty()) {
            int x = q.poll();

            for (int nx : list.get(x)) {
                if (check[nx] != 100_000_000) continue;

                check[nx] = check[x] + 1;
                q.add(nx);
            }
        }
    }

    public static void init() {
        check = new int[101];
        list = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            list.add(new ArrayList<>());
            check[i] = 100_000_000;
        }

        for (int i = 1; i < 100; i++) {
            if (hm.containsKey(i)) {
                list.get(i).add(hm.get(i));
                continue;
            }

            for (int j = 1; j <= 6; j++) {
                int nx = i + j;
                if(nx > 100) continue;

                while (hm.containsKey(nx)) nx = hm.get(nx);
                list.get(i).add(nx);
            }
        }
    }
}