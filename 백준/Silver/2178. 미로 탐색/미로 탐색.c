#include <stdio.h>
#define MAX 10000000

int N, M;
int map[101][101] = {0};
int dp[101][101] = {0};
int dx[] = {0, 0, 1, -1};
int dy[] = {1, -1, 0, 0};

void bfs() {
    int q1[100 * 100 + 1] = {0};
    int q2[100 * 100 + 1] = {0};
    int q3[100 * 100 + 1] = {1};
    int st = 0, ed = 0;

    q1[ed] = 0;
    q2[ed++] = 0;
    while (st < ed) {
        const int x = q1[st];
        const int y = q2[st];
        dp[x][y] = q3[st];
        st++;

        for (int i = 0; i < 4; i++) {
            const int nx = x + dx[i];
            const int ny = y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if (map[nx][ny] == 0 || dp[nx][ny] <= dp[x][y] + 1) continue;

            dp[nx][ny] = dp[x][y] + 1;
            q1[ed] = nx;
            q2[ed] = ny;
            q3[ed++] = dp[x][y] + 1;
        }
    }
}

int main(void) {
    scanf("%d %d", &N, &M);
    for (int i = 0; i < N; i++) {
        char temp[M];
        scanf("%s", temp);
        for (int j = 0; j < M; j++) {
            dp[i][j] = MAX;
            if (temp[j] == '1') map[i][j] = 1;
        }
    }

    bfs();
    printf("%d\n", dp[N - 1][M - 1]);
    return 0;
}
