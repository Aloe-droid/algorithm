#include <stdio.h>
#define MAX 10000000

int N, M;
int map[101][101] = {0};
int dp[101][101] = {MAX};
int dx[] = {0, 0, 1, -1};
int dy[] = {1, -1, 0, 0};

void dfs(const int x,const int y,const int t) {
    dp[x][y] = t;

    for (int i = 0; i < 4; i++) {
        const int nx = x + dx[i];
        const int ny = y + dy[i];
        if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
        if (map[nx][ny] == 0 || dp[nx][ny] <= t + 1) continue;
        dfs(nx, ny , t + 1);
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

    dfs(0, 0, 0);
    printf("%d\n", dp[N - 1][M - 1] + 1);
    return 0;
}
