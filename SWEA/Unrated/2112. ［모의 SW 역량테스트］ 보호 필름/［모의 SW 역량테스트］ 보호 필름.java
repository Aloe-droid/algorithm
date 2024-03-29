import java.io.*;
import java.util.*;

class Solution {
	static int N, M, K;
	static boolean flag;
	static int[][] ints;
	static int min;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			flag = false;
			min = Integer.MAX_VALUE;
			ints = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					ints[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0, 0);
			bw.write("#" + tc + " " + min + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dfs(int idx, int cnt) {
		if(min < cnt) return;
		
		if (idx == N) {
			if(!check()) return;
			min = Math.min(min, cnt);
			return;
		}
		
		int[] temp = ints[idx].clone();
		dfs(idx + 1, cnt);
		
		Arrays.fill(ints[idx], 1);
		dfs(idx + 1, cnt + 1);
		
		Arrays.fill(ints[idx], 0);
		dfs(idx + 1, cnt + 1);
		
		ints[idx] = temp;
	}

	public static boolean check() {
		for (int j = 0; j < M; j++) {
			int prev = ints[0][j];
			int cnt = 1;
			int maxCnt = 1;
			for (int i = 1; i < N; i++) {
				if(ints[i][j] == prev) cnt++;
				else cnt = 1;
				prev = ints[i][j];
				maxCnt = Math.max(maxCnt, cnt);
			}
			if (maxCnt < K) return false;
		}
		return true;
	}
}