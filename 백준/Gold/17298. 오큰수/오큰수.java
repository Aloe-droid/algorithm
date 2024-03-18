import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static Stack<Num> stack;
    public static int[] ints;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        stack = new Stack<>();
        ints = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Arrays.fill(ints, -1);
        for (int i = 0; i < N; i++) {
            Num num = new Num(i, Integer.parseInt(st.nextToken()));
            add(num);
        }

        for(int i : ints) sb.append(i).append(" ");
        System.out.println(sb);
    }

    public static void add(Num num) {
        while (!stack.isEmpty() && stack.peek().value < num.value) {
            ints[stack.pop().idx] = num.value;
        }
        stack.add(num);
    }
}

class Num {
    int idx;
    int value;

    Num(int idx, int value) {
        this.idx = idx;
        this.value = value;
    }
}