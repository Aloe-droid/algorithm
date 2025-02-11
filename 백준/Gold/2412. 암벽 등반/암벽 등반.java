import java.io.*;
import java.util.*;

class Main {
    static int N, T, R = 100_000_001;
    static Set<List<Integer>> rock;
    static Map<List<Integer>, Integer> visit;
    static Queue<int[]> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        rock = new HashSet<>();
        visit = new HashMap<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            rock.add(makeL(x, y));
        }
        System.out.println(bfs());
    }

    public static int bfs() {
        q = new LinkedList<>();

        q.add(new int[] {0, 0});
        visit.put(makeL(0, 0), 0);

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.poll()[1];
            List<Integer> point = makeL(x, y);
            if(y == T) return visit.get(point);

            for(int dx = -2; dx <= 2; dx++) {
                for(int dy = -2; dy <= 2; dy++) {
                    int nx = x + dx;
                    int ny = y + dy;
                    List<Integer> nextPoint = makeL(nx, ny);
                    if(nx < 0 || nx > R || ny < 0 || ny > T) continue;
                    if(!rock.contains(nextPoint) || visit.containsKey(nextPoint)) continue;

                    q.add(new int[] {nx, ny});
                    visit.put(nextPoint, visit.get(point) + 1);
                }
            }
        }
        return -1;
    }

    public static List<Integer> makeL(int x, int y) {
        List<Integer> list = new ArrayList<>();
        list.add(x);
        list.add(y);
        return list;
    }
}