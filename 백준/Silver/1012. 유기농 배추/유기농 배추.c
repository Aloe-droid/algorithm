#include <stdio.h>
#define MAX 50

int T, N, M, K;
int map[MAX + 1][MAX + 1];
int check[MAX + 1][MAX + 1];
int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};

void init() {
    for(int i = 0; i <= MAX; i++) {
        for(int j = 0; j <= MAX; j++) {
            map[i][j] = 0;
            check[i][j] = 0;
        }
    }
}

void bfs(int i, int j) {
    int q1[MAX * MAX + 1], q2[MAX * MAX + 1];
    int st = 0, ed = 0;
    
    check[i][j] = 1;
    q1[ed] = i;
    q2[ed++] = j;

    while(st < ed) {
        int x = q1[st];
        int y = q2[st++];

        for(int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            if(map[nx][ny] == 0 || check[nx][ny] == 1) continue;
            check[nx][ny] = 1;
            q1[ed] = nx;
            q2[ed++] = ny;
        }
    }
}

int main() {
    scanf("%d", &T);
    for(int tc = 1; tc <= T; tc++) {
        scanf("%d %d %d", &N, &M, &K);
        init();
        for(int i = 0; i < K; i++) {
            int x, y;
            scanf("%d %d", &x, &y);
            map[x][y] = 1;
        }

        int ans = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0 || check[i][j] == 1) continue;
                ans++;
                bfs(i, j);
            }
        }
        printf("%d\n", ans);
    }
    return 0;
}
