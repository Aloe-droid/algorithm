import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] ints;
    static Queue<int[]> q;
    static boolean[][] check;
    static List<List<int[]>> list;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N][N];
        check = new boolean[N][N];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ints[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (ints[i][j] == 0 || check[i][j]) continue;
                bfs(i, j);
            }
        }

        System.out.println(getMin());
    }

    public static void bfs(int x, int y) {
        q = new LinkedList<>();
        q.add(new int[]{x, y});
        check[x][y] = true;
        List<int[]> arrayList = new ArrayList<>();
        arrayList.add(new int[]{x, y});

        while (!q.isEmpty()) {
            x = q.peek()[0];
            y = q.poll()[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || check[nx][ny] || ints[nx][ny] == 0) continue;
                q.add(new int[]{nx, ny});
                arrayList.add(new int[]{nx, ny});
                check[nx][ny] = true;
            }
        }
        list.add(arrayList);
    }

    public static int getMin() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                List<int[]> l1 = list.get(i);
                List<int[]> l2 = list.get(j);

                for (int[] i1 : l1) {
                    for (int[] i2 : l2) {
                        int diff = Math.abs(i1[0] - i2[0]) + Math.abs(i1[1] - i2[1]);
                        min = Math.min(min, diff);
                    }
                }
            }
        }
        return min - 1;
    }
}