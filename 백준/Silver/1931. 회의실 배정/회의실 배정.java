import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] ints = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ints[i][0] = Integer.parseInt(st.nextToken());
			ints[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(ints, (o1, o2) -> {
			if (o1[1] < o2[1]) return -1;
			else if (o1[1] > o2[1]) return 1;
			else {
				if (o1[0] < o2[0]) return -1;
				else if (o1[0] > o2[0]) return 1;
				else return 0;
			}
		});
		
		int cnt = 0;
		int prev = -1;
		for(int i = 0; i < N; i++) {
			if(prev > ints[i][0]) continue;
			
			cnt++;
			prev = ints[i][1];
		}
		System.out.println(cnt);
	}
}