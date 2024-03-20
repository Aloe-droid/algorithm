import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] ints;
	static int P;
	static int idx;
	static PriorityQueue<Integer> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ints = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ints[i][0] = Integer.parseInt(st.nextToken());
			ints[i][1] = Integer.parseInt(st.nextToken());
		}
		P = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		Arrays.sort(ints, (o1, o2) -> {
			if (o1[0] < o2[0]) return -1;
			else if (o1[0] > o2[0]) return 1;
			else return 0;
		});
		
		int cnt = 0;
		idx = 0;
		q = new PriorityQueue<>(Collections.reverseOrder());
		add();
			
		while (!q.isEmpty() && P < L) {
			P += q.poll();
			cnt++;
			add();
		}
		System.out.println(P >= L ? cnt : -1);
	}
	
	public static void add() {
		for (int i = idx; i < N; i++) {
			if(ints[i][0] > P) return;
			idx++;
			q.add(ints[i][1]);
		}
	}
}
