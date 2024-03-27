import java.io.*;
import java.util.*;

class Solution {
	static int MAX = 100_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] ints = new int[N][N];
			for (int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					ints[i][j] = Integer.parseInt(st.nextToken());
					if (i != j && ints[i][j] == 0) ints[i][j] = MAX;
				}
			}
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N - 1; i++) {
					for (int j = i + 1; j < N; j++) {
						int sum = ints[i][k] + ints[k][j];
						ints[i][j] = ints[i][j] > sum ? sum : ints[i][j];
					}
				}
			}
			
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int temp = 0;
				for (int j = N - 1; j >= 0; j--) temp += j >= i ? ints[i][j] : ints[j][i];
				min = min > temp ? temp : min;
			}
			
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}
}