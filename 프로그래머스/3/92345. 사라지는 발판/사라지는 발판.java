import java.util.WeakHashMap;

class Solution {
    int N, M, min = Integer.MAX_VALUE;
    int[] a, b, dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    int[][] board;
    public int solution(int[][] board, int[] a, int[] b) {
        this.board = board;
        this.a = a;
        this.b = b;
        N = board.length;
        M = board[0].length;
        return dfs(true, 0).cnt;
    }

    public Turn dfs(boolean isATurn, int cnt) {
        int ax = a[0], ay = a[1], bx = b[0], by = b[1];
        // 패배 조건
        if((board[ax][ay] == 0 && isATurn) || (board[bx][by] == 0 && !isATurn)) return new Turn(false, cnt);

        int win = Integer.MAX_VALUE, lose = 0;
        int x = isATurn ? ax : bx;
        int y = isATurn ? ay : by;
        board[x][y] = 0;

        boolean canGo = false;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == 0) continue;
            canGo = true;

            Turn next;
            if(isATurn) {
                a[0] = nx; a[1] = ny;
                next = dfs(false, cnt + 1);
                a[0] = ax; a[1] = ay;
            }else{
                b[0] = nx; b[1] = ny;
                next = dfs(true, cnt + 1);
                b[0] = bx; b[1] = by;
            }

            // 다음 순번이 이기면 이번에 졌다는 의미, 최대의 움직임
            if(next.isWin) lose = Math.max(lose, next.cnt);
            // 다음 순번이 졌다면 이번에 이겼다는 의미, 최소의 움직임
            else win = Math.min(win, next.cnt);
        }

        board[x][y] = 1;
        // 1. 이동할 공간이 없는 경우
        if(!canGo) return new Turn(false, cnt);
        // 2. 현재 순번이 이기는 경우
        else if(win != Integer.MAX_VALUE) return new Turn(true, win);
        // 3. 현재 순번이 지는 경우
        else return new Turn(false, lose);
    }
}

class Turn {
    boolean isWin;
    int cnt;

    public Turn(boolean isWin, int cnt) {
        this.isWin = isWin;
        this.cnt = cnt;
    }
}
