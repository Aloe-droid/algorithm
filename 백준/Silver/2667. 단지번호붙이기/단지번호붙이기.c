#include <stdio.h>
#define NN 25

int N;
int quick[NN * NN + 1];
int map[NN + 1][NN + 1];
int check[NN + 1][NN + 1];
int dx[] = {0, 0, 1, -1};
int dy[] = {1, -1, 0, 0};

int bfs(const int i, const int j) {
    int cnt = 1;
    int q1[N * N + 1];
    int q2[N * N + 1];
    int st = 0, ed = 0;

    q1[ed] = i;
    q2[ed++] = j;

    while (st < ed) {
        const int x = q1[st];
        const int y = q2[st++];
        for (int k = 0; k < 4; k++) {
            const int nx = x + dx[k];
            const int ny = y + dy[k];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (map[nx][ny] == 0 || check[nx][ny] == 1) continue;
            q1[ed] = nx;
            q2[ed++] = ny;
            check[nx][ny] = 1;
            cnt += 1;
        }
    }
    return cnt;
}

void find() {
    for (int i = 1; i <= N * N; i++) {
        while (quick[i] > 0) {
            printf("%d\n", i);
            quick[i] -= 1;
        }
    }
}

int main(void) {
    scanf("%d", &N);
    for (int i = 0; i < N; i++) {
        char temp[N];
        scanf("%s", temp);
        for (int j = 0; j < N; j++) {
            map[i][j] = temp[j] - '0';
        }
    }

    int t = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (map[i][j] == 0 || check[i][j] == 1) continue;
            t += 1;
            check[i][j] = 1;
            const int cnt = bfs(i, j);
            quick[cnt] += 1;
        }
    }

    printf("%d\n", t);
    find();
    return 0;
}
