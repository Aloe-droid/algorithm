import java.io.*;
import java.util.*;

class Solution {
	static int N;
	static int X;
	static int[][] ints;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			cnt = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			ints = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					ints[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			check();
			bw.write("#" + tc + " " + cnt + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static void check() {
		rowCheck();
		colCheck();
	}
	
	public static void rowCheck() {
		for (int i = 0; i < N; i++) {
			boolean flag = true;
			boolean[] bools = new boolean[N];
			for (int j = 0; j < N; j++) {
				int value = ints[i][j];
				int left = j - 1 < 0 ? value : ints[i][j - 1];
				int right = j + 1 >= N ? value : ints[i][j + 1];
				if (value == left && value == right) continue;
				if (Math.abs(value - left) > 1 || Math.abs(value - right) > 1) {
					flag = false;
					break;
				}
				
				if (value > left && flag) {
					for (int k = j - 1; k > j - 1 - X; k--) {
						if (k < 0) {
							flag = false;
							break;
						}
						
						if(ints[i][k] == left && !bools[k]) {
							bools[k] = true;
						}else {
							flag = false;
							break;
						}
					}
				}
				
				if (value > right && flag) {
					for (int k = j + 1; k < j + 1 + X; k++) {
						if(k >= N) {
							flag = false;
							break;
						}
						
						if(ints[i][k] == right && !bools[k]) {
							bools[k] = true;
						}else {
							flag = false;
							break;
						}
					}
				}	
			}
			if(flag) cnt++;
		}
	}
	
	public static void colCheck() {
		for (int i = 0; i < N; i++) {
			boolean flag = true;
			boolean[] bools = new boolean[N];
			for (int j = 0; j < N; j++) {
				int value = ints[j][i];
				int top = j - 1 < 0 ? value : ints[j - 1][i];
				int bottom = j + 1 >= N ? value : ints[j + 1][i];
				if (value == top && value == bottom) continue;
				if (Math.abs(value - top) > 1 || Math.abs(value - bottom) > 1) {
					flag = false;
					break;
				}
				
				if (value > top && flag) {
					for (int k = j - 1; k > j - 1 - X; k--) {
						if (k < 0) {
							flag = false;
							break;
						}
						
						if(ints[k][i] == top && !bools[k]) {
							bools[k] = true;
						}else {
							flag = false;
							break;
						}
					}
				}
				
				if (value > bottom && flag) {
					for (int k = j + 1; k < j + 1 + X; k++) {
						if(k >= N) {
							flag = false;
							break;
						}
						
						if(ints[k][i] == bottom && !bools[k]) {
							bools[k] = true;
						}else {
							flag = false;
							break;
						}
					}
				}	
			}
			if(flag) cnt++;
		}
	}
}