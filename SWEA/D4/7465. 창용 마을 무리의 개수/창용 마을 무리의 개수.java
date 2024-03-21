import java.io.*;
import java.util.*;

class Solution {
	static int[] parents;
	static int[] ints;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			ints = new int[N + 1];
			parents = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				ints[i] = i;
				parents[i] = i;
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				union(x, y);
			}
			
			Set<Integer> hs = new HashSet<>();
			for(int i = 1; i <= N; i++) hs.add(find(i));
			bw.write("#" + tc + " " + hs.size() + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	public static void union(int x, int y) {
		int pX = find(x);
		int pY = find(y);
		parents[pX] = pY;
	}

	public static int find(int x) {
		if (x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
}