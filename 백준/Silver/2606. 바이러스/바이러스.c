#include <stdio.h>

int N, M;
int nears[101][101];
int check[101];

void bfs() {
    int q[101];
    int st = 0, ed = 0;

    q[ed++] = 1;
    check[1] = 1;
    while (st < ed) {
        const int x = q[st++];
        for (int i = 1; i <= N; i++) {
            if (nears[x][i] == 0 || check[i] == 1) continue;
            check[i] = 1;
            q[ed++] = i;
        }
    }
}

int find() {
    int cnt = 0;
    for (int i = 2; i <= N; i++) {
        if (check[i] == 1) cnt++;
    }
    return cnt;
}

int main(void) {
    scanf("%d", &N);
    scanf("%d", &M);

    for (int i = 0; i < M; i++) {
        int x, y;
        scanf("%d %d", &x, &y);
        nears[x][y] = 1;
        nears[y][x] = 1;
    }
    bfs();
    printf("%d", find());
    return 0;
}
