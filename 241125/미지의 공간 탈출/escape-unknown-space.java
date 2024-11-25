import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, F;
    static int[][] space, spaceDp;
    static int[][][] wall, wallDp;
    static int[] dx = new int[]{0, 0, 1, -1};
    static int[] dy = new int[]{1, -1, 0, 0};
    static Strange[] strangeArr;
    static Point start, end, spaceStart;
    static int EAST = 0;
    static int WEST = 1;
    static int SOUTH = 2;
    static int NORTH = 3;
    static int UP = 4;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        space = new int[N][N];
        spaceDp = new int[N][N];

        wall = new int[5][M][M];
        wallDp = new int[5][M][M];

        strangeArr = new Strange[F];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                if (space[i][j] == 1) spaceDp[i][j] = Integer.MAX_VALUE;
                if (space[i][j] == 4) end = new Point(i, j);
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    wall[i][j][k] = Integer.parseInt(st.nextToken());
                    if (wall[i][j][k] == 1) wallDp[i][j][k] = Integer.MAX_VALUE;
                    if (wall[i][j][k] == 2) start = new Point(i, j, k);
                }
            }
        }

        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            Strange strange = new Strange(x, y, dir, v);
            strangeArr[i] = strange;
        }

        if (start == null) {
            System.out.println("-1");
            return;
        }

        walkWall();
        int bias = findSpace();
        if (bias == -1) throw new RuntimeException("-1이 있을 수 없음.");
        walkSpace(bias);

        int value = spaceDp[end.x][end.y];
        if (value == 0 || value == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(value);
    }

    public static void walkWall() {
        Map<Point, Set<Point>> hm = initMoveNext();
        Queue<Point> q = new LinkedList<>();
        wallDp[start.dir][start.x][start.y] = 1;
        q.add(start);

        while (!q.isEmpty()) {
            Point prev = q.poll();
            int dir = prev.dir;
            for (int i = 0; i < 4; i++) {
                int nx = prev.x + dx[i];
                int ny = prev.y + dy[i];

                if (nx >= 0 && nx < M && ny >= 0 && ny < M && wallDp[dir][nx][ny] == 0) {
                    wallDp[dir][nx][ny] = wallDp[dir][prev.x][prev.y] + 1;
                    q.add(new Point(dir, nx, ny));
                } else {
                    for (Point key : hm.keySet()) {
                        if (key.dir == prev.dir && key.x == prev.x && key.y == prev.y) {
                            for (Point p : hm.get(key)) {
                                if (wallDp[p.dir][p.x][p.y] == 0) {
                                    wallDp[p.dir][p.x][p.y] = wallDp[prev.dir][prev.x][prev.y] + 1;
                                    q.add(p);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static Map<Point, Set<Point>> initMoveNext() {
        Map<Point, Set<Point>> hm = new HashMap<>();

        for (int i = 0; i < M; i++) {
            // 위쪽 -> 북쪽으로 이동 (위로 이동할 때)
            Point p1 = new Point(UP, 0, i);
            Point p2 = new Point(NORTH, 0, M - 1 - i);
            // 위쪽에서 동쪽으로 이동 (오른쪽으로 이동할 때)
            Point p3 = new Point(UP, i, M - 1);
            Point p4 = new Point(EAST, 0, M - 1 - i);
            // 위쪽에서 남쪽으로 이동 (아래로 이동할 때)
            Point p5 = new Point(UP, M - 1, i);
            Point p6 = new Point(SOUTH, 0, i);
            // 위쪽에서 서쪽으로 이동 (왼쪽으로 이동할 때)
            Point p7 = new Point(UP, i, 0);
            Point p8 = new Point(WEST, 0, i);

            // 남쪽에서 동쪽으로 이동 (오른쪽으로 이동할 때)
            Point p9 = new Point(SOUTH, i, M - 1);
            Point p10 = new Point(EAST, i, 0);
            // 동쪽에서 북쪽으로 이동 (오른쪽으로 이동할 때)
            Point p11 = new Point(EAST, i, M - 1);
            Point p12 = new Point(NORTH, i, 0);
            // 북쪽에서 서쪽으로 이동 (오른쪽으로 이동할 때)
            Point p13 = new Point(NORTH, i, M - 1);
            Point p14 = new Point(WEST, i, 0);
            // 서쪽에서 남쯕으로 이동 (오른쪽으로 이동할 때)
            Point p15 = new Point(WEST, i, M - 1);
            Point p16 = new Point(SOUTH, i, 0);

            makeSet(hm, p1, p2);
            makeSet(hm, p3, p4);
            makeSet(hm, p5, p6);
            makeSet(hm, p7, p8);
            makeSet(hm, p9, p10);
            makeSet(hm, p11, p12);
            makeSet(hm, p13, p14);
            makeSet(hm, p15, p16);
        }
        return hm;
    }

    public static void makeSet(Map<Point, Set<Point>> hm, Point p1, Point p2) {
        if (!hm.containsKey(p1)) hm.put(p1, new HashSet<>());
        if (!hm.containsKey(p2)) hm.put(p2, new HashSet<>());

        Set<Point> hs1 = hm.get(p1);
        Set<Point> hs2 = hm.get(p2);
        hs1.add(p2);
        hs2.add(p1);
    }

    public static int findSpace() {
        int x = -1;
        int y = -1;

        outer:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (space[i][j] == 3) {
                    x = i;
                    y = j;
                    break outer;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (space[i][j] != 0) continue;

                int dir = -1;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                    if (space[nx][ny] == 3) {
                        dir = k;
                        break;
                    }
                }

                if (dir == -1) continue;

                spaceStart = new Point(i, j);
                if (y > j) {
                    // 왼쪽에 있는 경우
                    return wallDp[WEST][M - 1][i - x] - 1;
                } else if (y + M - 1 < j) {
                    // 오른쪽에 있는 경우
                    return wallDp[EAST][M - 1][M - 1 - (i - x)] - 1;
                } else if (x > i) {
                    // 위쪽에 있는 경우
                    return wallDp[NORTH][M - 1][M - 1 - (j - y)] - 1;
                } else if (x + M - 1 < i) {
                    // 아래쪽에 있는 경우
                    return wallDp[SOUTH][M - 1][j - y] - 1;
                }
            }
        }
        return -1;
    }

    public static void walkSpace(int bias) {
        int maxCnt = 20_001;
        spaceDp = new int[N][N];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(spaceStart.x, spaceStart.y));
        spaceDp[spaceStart.x][spaceStart.y] = bias + 1;


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (space[i][j] != 0 & space[i][j] != 4) spaceDp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (Strange strange : strangeArr) {
            spaceDp[strange.x][strange.y] = Integer.MAX_VALUE;
        }


        for (int i = 0; i < maxCnt; i++) {
            for (Strange strange : strangeArr) {
                if (strange.v <= i && i % strange.v == 0 && strange.v > 0) {
                    int nx = strange.x + dx[strange.dir] * (i / strange.v);
                    int ny = strange.y + dy[strange.dir] * (i / strange.v);

                    if (nx == end.x && ny == end.y) continue;
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || spaceDp[nx][ny] == Integer.MAX_VALUE) {
                        strange.v = -1;
                    } else {
                        spaceDp[nx][ny] = Integer.MAX_VALUE;
                    }
                }
            }

            if (i <= bias) continue;

            List<Point> next = new ArrayList<>();
            while (!q.isEmpty()) {
                Point prev = q.poll();
                if (spaceDp[prev.x][prev.y] == Integer.MAX_VALUE) continue;

                for (int k = 0; k < 4; k++) {
                    int nx = prev.x + dx[k];
                    int ny = prev.y + dy[k];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || spaceDp[nx][ny] != 0) continue;
                    spaceDp[nx][ny] = spaceDp[prev.x][prev.y] + 1;
                    next.add(new Point(nx, ny));
                }
            }

            q.addAll(next);
            if (spaceDp[end.x][end.y] != 0) break;
        }
    }
}

class Point {
    int x, y, dir;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int dir, int x, int y) {
        this.dir = dir;
        this.x = x;
        this.y = y;
    }
}

class Strange {
    int x, y, dir, v;

    public Strange(int x, int y, int dir, int v) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.v = v;
    }
}