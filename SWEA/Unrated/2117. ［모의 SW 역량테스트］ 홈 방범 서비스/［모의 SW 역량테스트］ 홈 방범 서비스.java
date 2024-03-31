import java.io.*;
import java.util.*;

class Solution {
    static int N, M;
    static int[][] ints;
    static List<int[]> homes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            homes = new ArrayList<>();
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            ints = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    ints[i][j] = Integer.parseInt(st.nextToken());
                    if (ints[i][j] == 1) homes.add(new int[]{i, j});
                }
            }

            int house = 0;
            for (int k = 22; k > 0; k--) {
                int cost = k * k + (k - 1) * (k - 1);

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        int cnt = getHouse(i, j, k);
                        if (cnt * M < cost) continue;
                        house = Math.max(house, cnt);
                    }
                }
            }

            bw.write("#" + tc + " " + house + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getHouse(int x, int y, int t) {
        int cnt = 0;
        for (int[] home : homes) {
            if (Math.abs(home[0] - x) + Math.abs(home[1] - y) < t) cnt++;
        }
        return cnt;
    }
}