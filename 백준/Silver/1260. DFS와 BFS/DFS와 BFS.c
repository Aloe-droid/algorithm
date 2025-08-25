#include <stdio.h>

int N, M, s;
int graph[1001][1001] = { 0 };
int visitDFS[1001] = { 0 };
int visitBFS[1001] = { 0 };

void dfs(const int k) {
    visitDFS[k] = 1;
    printf("%d ", k);

    for (int i = 1; i <= N; i++) {
        if (graph[k][i] == 0 || visitDFS[i] == 1) continue;
        dfs(i);
    }
}

void bfs(const int k) {
    int q[1001];
    int front = 0, back = 0;

    visitBFS[k] = 1;
    q[back++] = k;

    while (front < back) {
        const int p = q[front++];
        printf("%d ", p);

        for (int i = 1; i <= N; i++) {
            if (graph[p][i] == 0 || visitBFS[i] == 1) continue;

            visitBFS[i] = 1;
            q[back++] = i;
        }
    }
}

int main(void) {
    scanf("%d %d %d", &N, &M, &s);
    for (int i = 0; i < M; i++) {
        int x, y;
        scanf("%d %d", &x, &y);
        graph[x][y] = 1;
        graph[y][x] = 1;
    }

    dfs(s);
    printf("\n");
    bfs(s);
    return 0;
}
