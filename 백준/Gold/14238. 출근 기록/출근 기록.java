import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    static int[] works;
    static char[] ans;
    static boolean[][][][][] check;
    static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        works = new int[3];
        char[] chars = br.readLine().toCharArray();
        for (char c : chars) works[c - 'A'] += 1;

        ans = new char[chars.length];
        check = new boolean[works[0] + 1][works[1] + 1][works[2] + 1][3][3];

        dfs(0, 0, 0);
        System.out.println(flag ? String.valueOf(ans) : -1);
    }

    // 해당 시간에 A, B, C 중에 하나가 있다는 것 == 이미 탐색했다는 것 ㅋ
    public static void dfs(int day, int pp, int p) {
        if (day == ans.length) {
            flag = true;
            return;
        }

        // A
        if (works[0] > 0 && !flag && !check[works[0] - 1][works[1]][works[2]][pp][p]) {
            works[0] -= 1;
            check[works[0]][works[1]][works[2]][pp][p] = true;
            ans[day] = 'A';
            dfs(day + 1, p, 0);
            works[0] += 1;
        }

        // B
        if (p != 1 && works[1] > 0 && !flag && !check[works[0]][works[1] - 1][works[2]][pp][p]) {
            works[1] -= 1;
            check[works[0]][works[1]][works[2]][pp][p] = true;
            ans[day] = 'B';
            dfs(day + 1, p, 1);
            works[1] += 1;
        }

        // C
        if (p != 2 && pp != 2 && works[2] > 0 && !flag && !check[works[0]][works[1]][works[2] - 1][pp][p]) {
            works[2] -= 1;
            check[works[0]][works[1]][works[2]][pp][p] = true;
            ans[day] = 'C';
            dfs(day + 1, p, 2);
            works[2] += 1;
        }
    }
}
