import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		List<int[]> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.add(new int[] {x, y});
		}
		
		list.sort((int[] i1, int[] i2) -> {
			if(i1[1] < i2[1]) return -1;
			else if(i1[1] > i2[1]) return 1;
			else {
				if(i1[0] < i2[0]) return -1;
				else if(i1[0] > i2[0]) return 1;
				else return 0;
			}
		});
		
		for(int[] ints : list) {
			sb.append(ints[0]).append(" ").append(ints[1]).append("\n");
		}
		System.out.println(sb);
	}
}