
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, sX, sY, eX, eY;
    static Place[][] map;
    static int[][] seeMap, mapDp;
    static Soldier[] soldiers;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sX = Integer.parseInt(st.nextToken());
        sY = Integer.parseInt(st.nextToken());
        eX = Integer.parseInt(st.nextToken());
        eY = Integer.parseInt(st.nextToken());

        map = new Place[N][N];
        mapDp = new int[N][N];
        soldiers = new Soldier[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Soldier soldier = new Soldier(i, x, y);
            soldiers[i] = soldier;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int k = Integer.parseInt(st.nextToken());
                boolean isRoad = k == 0;
                Place place = new Place(i, j, isRoad);
                map[i][j] = place;
                if (!map[i][j].isRoad) mapDp[i][j] = Integer.MAX_VALUE;
            }
        }

        init();
        if (mapDp[sX][sY] == Integer.MAX_VALUE || mapDp[sX][sY] == 0) {
            System.out.println(-1);
            return;
        }

        for (Soldier soldier : soldiers) {
            map[soldier.x][soldier.y].soldierList.add(soldier);
        }

        while (sX != eX || sY != eY) {

            // 1. 메두사 이동
            boolean isArrive = move();
            if (isArrive) break;

            // 2. 메두사 시선
            int stopCount = see();

            // 3. 전사 이동 (1)
            int moveCount = moveSoldier(true);

            // 4. 전사 이동 (2)
            moveCount += moveSoldier(false);

            // 5. 전사 공격
            int killCount = attack();

            // 6. 돌 초기화
            for (Soldier soldier : soldiers) if (soldier != null) soldier.isStone = false;

            // 7. 출력물 추가
            sb.append(moveCount).append(" ").append(stopCount).append(" ").append(killCount).append("\n");
        }

        sb.append("0");
        System.out.println(sb);
    }

    public static void init() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{eX, eY});
        mapDp[eX][eY] = 1;

        while (!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.poll()[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || mapDp[nx][ny] != 0 || !map[nx][ny].isRoad) continue;

                mapDp[nx][ny] = mapDp[x][y] + 1;
                q.add(new int[]{nx, ny});
            }
        }
    }

    public static boolean move() {
        int prev = mapDp[sX][sY];

        for (int i = 0; i < 4; i++) {
            int nx = sX + dx[i];
            int ny = sY + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || mapDp[nx][ny] != prev - 1) continue;

            List<Soldier> list = map[nx][ny].soldierList;
            for (Soldier soldier : list) {
                soldiers[soldier.idx] = null;
            }
            map[nx][ny].soldierList.clear();

            sX = nx;
            sY = ny;
            break;
        }

        return sX == eX && sY == eY;
    }

    public static int see() {
        List<Set<Soldier>> list = new ArrayList<>();
        int[][][] temp = new int[4][N][N];
        for (int i = 0; i < 4; i++) list.add(new HashSet<>());

        // 위쪽 바라볼 때
        seeNorth(temp[0], list.get(0));
        // 아래쪽 바라볼 때
        seeSouth(temp[1], list.get(1));
        // 왼쪽 바라볼 때
        seeWest(temp[2], list.get(2));
        // 오른쪽 바라볼 때
        seeEast(temp[3], list.get(3));

        int max = -1;
        for (int i = 0; i < 4; i++) max = Math.max(max, list.get(i).size());

        for (int i = 0; i < 4; i++) {
            if (list.get(i).size() == max) {
                for (Soldier soldier : list.get(i)) {
                    soldier.isStone = true;
                }
                seeMap = temp[i];
                return list.get(i).size();
            }
        }

        return 0;
    }

    public static void seeNorth(int[][] temp, Set<Soldier> hs) {
        if (sX == 0) return;

        int distance = 2;
        for (int i = sX - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                int diff = Math.abs(i - sX) + Math.abs(j - sY);
                if (diff > distance || temp[i][j] != 0) continue;

                temp[i][j] = -1;
                if (map[i][j].soldierList.isEmpty()) continue;

                hs.addAll(map[i][j].soldierList);
                if (i == 0) continue;

                int bias = 1;
                for (int m = i - 1; m >= 0; m--) {
                    int startY, endY;
                    if (j < sY) startY = j - bias;
                    else startY = j;

                    if (j > sY) endY = j + bias;
                    else endY = j;

                    for (int n = startY; n <= endY; n++) {
                        if (m >= N || n < 0 || n >= N) continue;
                        temp[m][n] = 1;
                    }
                    bias++;
                }
            }
            distance += 2;
        }
    }

    public static void seeSouth(int[][] temp, Set<Soldier> hs) {
        if (sX == N - 1) return;

        int distance = 2;
        for (int i = sX + 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int diff = Math.abs(i - sX) + Math.abs(j - sY);
                if (diff > distance || temp[i][j] != 0) continue;

                temp[i][j] = -1;
                if (map[i][j].soldierList.isEmpty()) continue;

                hs.addAll(map[i][j].soldierList);
                if (i == N - 1) continue;

                int bias = 1;
                for (int m = i + 1; m < N; m++) {
                    int startY, endY;
                    if (j < sY) startY = j - bias;
                    else startY = j;

                    if (j > sY) endY = j + bias;
                    else endY = j;

                    for (int n = startY; n <= endY; n++) {
                        if (m < 0 || m >= N || n < 0 || n >= N) continue;
                        temp[m][n] = 1;
                    }
                    bias++;
                }
            }
            distance += 2;
        }
    }

    public static void seeWest(int[][] temp, Set<Soldier> hs) {
        if (sY == 0) return;

        int distance = 2;
        for (int i = sY - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                int diff = Math.abs(i - sY) + Math.abs(j - sX);
                if (diff > distance || temp[j][i] != 0) continue;

                temp[j][i] = -1;
                if (map[j][i].soldierList.isEmpty()) continue;

                hs.addAll(map[j][i].soldierList);
                if (i == 0) continue;

                int bias = 1;
                for (int m = i - 1; m >= 0; m--) {
                    int startX, endX;
                    if (j < sX) startX = j + bias;
                    else startX = j;

                    if (j > sX) endX = j + bias;
                    else endX = j;

                    for (int n = startX; n <= endX; n++) {
                        if (m >= N || n < 0 || n >= N) continue;
                        temp[n][m] = 1;
                    }
                    bias++;
                }
            }
            distance += 2;
        }
    }

    public static void seeEast(int[][] temp, Set<Soldier> hs) {
        if (sY == N - 1) return;

        int distance = 2;
        for (int i = sY + 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int diff = Math.abs(i - sY) + Math.abs(j - sX);
                if (diff > distance || temp[j][i] != 0) continue;

                temp[j][i] = -1;
                if (map[j][i].soldierList.isEmpty()) continue;

                hs.addAll(map[j][i].soldierList);
                if (i == N - 1) continue;

                int bias = 1;
                for (int m = i + 1; m < N; m++) {
                    int startX, endX;
                    if (j < sX) startX = j + bias;
                    else startX = j;

                    if (j > sX) endX = j + bias;
                    else endX = j;

                    for (int n = startX; n <= endX; n++) {
                        if (m < 0 || m >= N || n < 0 || n >= N) continue;
                        temp[n][m] = 1;
                    }
                    bias++;
                }
            }
            distance += 2;
        }
    }

    public static int moveSoldier(boolean isFirst) {
        int[] dir = {3, 2, 1, 0};
        if (!isFirst) dir = new int[]{1, 0, 3, 2};

        int count = 0;
        for (Soldier soldier : soldiers) {
            if (soldier == null || soldier.isStone) continue;
            if(soldier.x == sX && soldier.y == sY) continue;

            int prevDiff = Math.abs(soldier.x - sX) + Math.abs(soldier.y - sY);
            int min = prevDiff;
            int nextX = -1, nextY = -1;

            for (int i = 0; i < 4; i++) {
                int nx = soldier.x + dx[dir[i]];
                int ny = soldier.y + dy[dir[i]];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(seeMap[nx][ny] == -1) continue;

                int diff = Math.abs(nx - sX) + Math.abs(ny - sY);
                if (diff <= min) {
                    min = diff;
                    nextX = nx;
                    nextY = ny;
                }
            }

            if (min != prevDiff) {
                map[soldier.x][soldier.y].soldierList.remove(soldier);
                map[nextX][nextY].soldierList.add(soldier);
                soldier.x = nextX;
                soldier.y = nextY;
                count++;
            }
        }
        return count;
    }

    public static int attack() {
        int count = 0;

        for (Soldier soldier : soldiers) {
            if (soldier == null) continue;
            if (soldier.x != sX || soldier.y != sY) continue;

            map[soldier.x][soldier.y].soldierList.remove(soldier);
            soldiers[soldier.idx] = null;
            count++;
        }
        return count;
    }
}


class Place {
    int x, y;
    boolean isRoad;
    List<Soldier> soldierList;

    public Place(int x, int y, boolean isRoad) {
        this.x = x;
        this.y = y;
        this.isRoad = isRoad;
        soldierList = new ArrayList<>();
    }
}


class Soldier {
    int x, y, idx;
    boolean isStone;

    public Soldier(int idx, int x, int y) {
        this.idx = idx;
        this.x = x;
        this.y = y;
        isStone = false;
    }
}
