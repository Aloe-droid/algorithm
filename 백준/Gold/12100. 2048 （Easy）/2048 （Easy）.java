import java.io.*;
import java.util.*;

class Main {
    static int N, max = 0;
    static int[][] ints;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                ints[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(max);
    }

    public static void dfs(int i) {
        if(i == 5) return;

        for(int k = 0; k < 4; k++) {
            int[][] prev = ints;
            move(k);
            check();
            dfs(i + 1);
            ints = prev;
        }
    }

    public static void move(int dir) {
        if(dir == 0) move0();
        else if(dir == 1) move1();
        else if(dir == 2) move2();
        else if(dir == 3) move3();
    }

    // 위로 올리기
    public static void move0() {
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < N; i++) list.add(new ArrayList<>());

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(ints[j][i] != 0) list.get(i).add(ints[j][i]);
            }
        }

        sum(list);

        int[][] newInts = new int[N][N];
        for(int i = 0; i < N; i++) {
            List<Integer> l = list.get(i);
            int idx = 0;

            for(int j = 0; j < l.size(); j++) {
                if(l.get(j) == 0) continue;

                newInts[idx][i] = l.get(j);
                idx += 1;
            }
        }
        ints = newInts;
    }

    // 아래로 이동
    public static void move1() {
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < N; i++) list.add(new ArrayList<>());

        for(int i = 0; i < N; i++) {
            for(int j = N - 1; j >= 0; j--) {
                if(ints[j][i] != 0) list.get(i).add(ints[j][i]);
            }
        }

        sum(list);

        int[][] newInts = new int[N][N];
        for(int i = 0; i < N; i++) {
            List<Integer> l = list.get(i);
            int idx = N - 1;

            for(int j = 0; j < l.size(); j++) {
                if(l.get(j) == 0) continue;

                newInts[idx][i] = l.get(j);
                idx -= 1;
            }
        }
        ints = newInts;
    }

    // 왼쪽으로 이동
    public static void move2() {
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < N; i++) list.add(new ArrayList<>());

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(ints[i][j] != 0) list.get(i).add(ints[i][j]);
            }
        }

        sum(list);
        int[][] newInts = new int[N][N];
        for(int i = 0; i < N; i++) {
            List<Integer> l = list.get(i);
            int idx = 0;

            for(int j = 0; j < l.size(); j++) {
                if(l.get(j) == 0) continue;

                newInts[i][idx] = l.get(j);
                idx += 1;
            }
        }
        ints = newInts;
    }

    // 오른쪽으로 이동
    public static void move3() {
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < N; i++) list.add(new ArrayList<>());

        for(int i = 0; i < N; i++) {
            for(int j = N - 1; j >= 0; j--) {
                if(ints[i][j] != 0) list.get(i).add(ints[i][j]);
            }
        }

        sum(list);
        int[][] newInts = new int[N][N];
        for(int i = 0; i < N; i++) {
            List<Integer> l = list.get(i);
            int idx = N - 1;

            for(int j = 0; j < l.size(); j++) {
                if(l.get(j) == 0) continue;

                newInts[i][idx] = l.get(j);
                idx -= 1;
            }
        }
        ints = newInts;
    }

    public static void sum(List<List<Integer>> list) {
        for(int i = 0; i < N; i++) {
            List<Integer> l = list.get(i);

            for(int j = 0; j < l.size() - 1; j++) {
                int i1 = l.get(j);
                int i2 = l.get(j + 1);

                if(i1 == i2) {
                    l.set(j, i1 * 2);
                    l.set(j + 1, 0);
                    j += 1;
                }
            }
        }
    }

    public static void check() {
        for(int[] ii : ints) {
            for(int i : ii) {
                max = Math.max(i, max);
            }
        }
    }

    public static void print() {
        for(int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(ints[i]));
        }
    }
}