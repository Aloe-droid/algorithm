import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static List<List<Integer>> list;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());

		list = new ArrayList<>();
		for (int i = 0; i <= N; i++) list.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.get(x).add(y);
			list.get(y).add(x);
		}
		
		for(int i = 1; i <= N; i++) {
			List<Integer> al = list.get(i);
			Collections.sort(al);
		}
		
		check = new boolean[N + 1];
		check[start] = true;
		sb.append(start).append(" ");
		dfs(sb, start);
		
		sb.append("\n");
		check = new boolean[N + 1];
		check[start] = true;
		
		sb.append(start).append(" ");
		bfs(sb, start);
		
		System.out.println(sb);
	}

	public static void dfs(StringBuilder sb, int idx) {
		for(int i : list.get(idx)) {
			if(check[i]) continue;
			check[i] = true;
			sb.append(i).append(" ");
			dfs(sb, i);
		}
	}
	
	public static void bfs(StringBuilder sb, int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);

		while (!q.isEmpty()) {
			int x = q.poll();
			for (int y : list.get(x)) {
				if (check[y]) continue;
				check[y] = true;
				sb.append(y).append(" ");
				q.add(y);
			}
		}
	}
}