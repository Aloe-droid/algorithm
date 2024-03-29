import java.io.*;
import java.util.*;

class Solution {
	static int N;
	static int[][] ints;
	static List<int[]> list;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int cnt, len;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			ints = new int[N][N];
			list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					ints[i][j] = Integer.parseInt(st.nextToken());
					if (ints[i][j] == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1) list.add(new int[] { i, j });
				}
			}
			cnt = 0;
			len = Integer.MAX_VALUE;
			dfs(0, 0, 0);
			bw.write("#" + tc + " " + len + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dfs(int start, int idx, int count) {
		if (idx == list.size()) {
			if (cnt < count) {
				cnt = count;
				len = check();
			} else if (cnt == count) {
				len = Math.min(len, check());
			}
		}
		
		for (int i = start; i < list.size(); i++) {
			int x = list.get(i)[0];
			int y = list.get(i)[1];
			
			for (int j = 0; j < 4; j++) {
				if(!canDraw(x, y, j)) continue;
				draw(x, y, j, true);
				dfs(i + 1, idx + 1, count + 1);
				draw(x, y, j, false);
			}
			dfs(i + 1, idx + 1, count);
		}
	}
	
	public static boolean canDraw(int x, int y, int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		while(nx >= 0 && nx < N && ny >= 0 && ny < N) {
			if(ints[nx][ny] != 0) return false; 
			nx += dx[dir];
			ny += dy[dir];
		}
		return true;
	}
	
	public static void draw(int x, int y, int dir, boolean flag) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		while(nx >= 0 && nx < N && ny >= 0 && ny < N) {
			ints[nx][ny] = flag ? 2 : 0;
			nx += dx[dir];
			ny += dy[dir];
		}
	}
	
	public static int check() {
		int count = 0;
		for (int[] ii : ints) for (int i : ii) if (i == 2) count++;
		return count;
	}
}