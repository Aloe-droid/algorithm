import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static String[] strings;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            strings = new String[10001];


            find(a);
            sb.append(strings[b]).append("\n");
        }
        System.out.println(sb);
    }

    public static void find(int k) {
        Queue<Integer> q = new LinkedList<>();
        q.add(k);
        strings[k] = "";

        while (!q.isEmpty()) {
            int x = q.poll();

            // D
            int nx = (2 * x) % 10000;
            if (strings[nx] == null) {
                q.add(nx);
                strings[nx] = strings[x] + "D";
            }

            // S
            nx = x == 0 ? 9999 : x - 1;
            if (strings[nx] == null) {
                q.add(nx);
                strings[nx] = strings[x] + "S";
            }

            // L
            nx = (x % 1000) * 10 + x / 1000;
            if (strings[nx] == null) {
                q.add(nx);
                strings[nx] = strings[x] + "L";
            }

            // R
            nx = (x % 10) * 1000 + x / 10;
            if (strings[nx] == null) {
                q.add(nx);
                strings[nx] = strings[x] + "R";
            }
        }
    }
}