import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[] ints, minTree, maxTree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ints = new int[N + 1];
        minTree = new int[N * 4];
        maxTree = new int[N * 4];

        for(int i = 1; i <= N; i++) ints[i] = Integer.parseInt(br.readLine());
        init(1, 1, N);

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int min = searchMinValue(1, 1, N, x, y);
            int max = searchMaxValue(1, 1, N, x, y);
            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    public static void init(int node, int left, int right) {
        if(left == right) {
            minTree[node] = ints[left];
            maxTree[node] = ints[left];
            return;
        }

        int mid = (left + right) / 2;
        init(node * 2, left, mid);
        init(node * 2 + 1, mid + 1, right);
        minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
        maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
    }

    public static int searchMinValue(int node, int left, int right, int i, int j) {
        if(i > right || j < left) return Integer.MAX_VALUE;
        if(i <= left && j >= right) return minTree[node];

        int mid = (left + right) / 2;
        int v1 = searchMinValue(node * 2, left, mid, i, j);
        int v2 = searchMinValue(node * 2 + 1, mid + 1, right, i ,j);
        return Math.min(v1, v2);
    }

    public static int searchMaxValue(int node, int left, int right, int i, int j) {
        if(i > right || j < left) return Integer.MIN_VALUE;
        if(i <= left && j >= right) return maxTree[node];

        int mid = (left + right) / 2;
        int v1 = searchMaxValue(node * 2, left, mid, i, j);
        int v2 = searchMaxValue(node * 2 + 1, mid + 1, right, i ,j);
        return Math.max(v1, v2);
    }

}
