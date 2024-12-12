import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int M, N, T;
    static int[][] ints, temp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        ints = new int[N][M];
        temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ints[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            spread();
            wind();
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (ints[i][j] != 0 && ints[i][j] != -1) cnt += ints[i][j];
            }
        }
        System.out.println(cnt);
    }

    public static void spread() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (ints[i][j] == 0) continue;

                int v = ints[i][j] / 5;
                int c = 0;

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M || ints[nx][ny] == -1) continue;
                    temp[nx][ny] += v;
                    c += 1;
                }
                temp[i][j] -= v * c;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ints[i][j] += temp[i][j];
                temp[i][j] = 0;
            }
        }
    }

    public static void wind() {
        for (int i = 0; i < N; i++) {
            if (ints[i][0] != -1) continue;

            // 오른쪽으로 이동
            int p1 = move(i, 1, 0, 0);
            int p2 = move(i + 1, 1, 0, 0);

            // 위로 이동
            int p3 = move(i - 1, M - 1, p1, 1);

            // 아래쪽으로 이동
            int p4 = move(i + 2, M - 1, p2, 2);

            // 왼쪽으로 이동
            int p5 = move(0, M - 2, p3, 3);
            int p6 = move(N - 1, M - 2, p4, 3);

            // 아래쪽으로 이동
            for(int j = 1; j < i; j++){
                int temp = ints[j][0];
                ints[j][0] = p5;
                p5 = temp;
            }

            // 위쪽으로 이동
            for(int j = N - 2; j > i + 1; j--){
                int temp = ints[j][0];
                ints[j][0] = p6;
                p6 = temp;
            }

            i++;
        }
    }

    // 0 : 오른쪽, 1 : 위쪽, 2 : 아래쪽, 3 : 왼쪽
    public static int move(int sX, int sY, int prev, int dir) {
        if (dir == 0) {
            for (int i = sY; i < M; i++) {
                int temp = ints[sX][i];
                ints[sX][i] = prev;
                prev = temp;
            }
            return prev;
        } else if (dir == 1) {
            for (int i = sX; i >= 0; i--) {
                int temp = ints[i][sY];
                ints[i][sY] = prev;
                prev = temp;
            }
            return prev;
        } else if (dir == 2) {
            for (int i = sX; i < N; i++) {
                int temp = ints[i][sY];
                ints[i][sY] = prev;
                prev = temp;
            }
            return prev;
        } else if (dir == 3) {
            for (int i = sY; i >= 0; i--) {
                int temp = ints[sX][i];
                ints[sX][i] = prev;
                prev = temp;
            }
            return prev;
        }

        return -1;
    }
}