import java.io.*;
import java.util.*;

class Main {
    static int N, M, sX = -1, sY = -1, eX = -1, eY = -1, max = 100_000_000;
    static char[][] chars;
    static int[][][] ints;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        chars = new char[N][M];
        ints = new int[N][M][4];

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                chars[i][j] = s.charAt(j);
                Arrays.fill(ints[i][j], max);

                if(chars[i][j] != 'C') continue;
                if(sX == -1 && sY == -1) {
                    sX = i;
                    sY = j;
                }else{
                    eX = i;
                    eY = j;
                }
            }
        }
        bfs();

        int min = max;
        for(int i = 0; i < 4; i++) min = Math.min(min, ints[eX][eY][i]);
        System.out.println(min);
    }

    public static void bfs() {
        pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[3]));
        for(int i = 0; i < 4; i++) {
            ints[sX][sY][i] = 0;
            pq.add(new int[] { sX, sY, i, ints[sX][sY][i] });
        }

        while(!pq.isEmpty()) {
            int x = pq.peek()[0];
            int y = pq.peek()[1];
            int dir = pq.peek()[2];
            int w = pq.poll()[3];

            if(ints[x][y][dir] < w) continue;
            for(int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                int nW = w;
                if(dir != k) nW += 1;

                if(nx < 0 || nx >= N || ny < 0 || ny >= M || chars[nx][ny] == '*') continue;
                if(ints[nx][ny][k] > nW) {
                    ints[nx][ny][k] = nW;
                    pq.add(new int[] { nx, ny, k, nW });
                }
            }
        }
    }
}
