import java.io.*;
import java.util.*;

class Main {
    static int N;
    static Word init;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        init = new Word("-");

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            Word word = init;
            for(int j = 0; j < K; j++) {
                String s = st.nextToken();
                Word w = null;
                for(Word nxt : word.next) {
                    if(nxt.s.equals(s)) {
                        w = nxt;
                        break;
                    }
                }

                if(w == null) {
                    w = new Word(s);
                    word.next.add(w);
                }

                word = w;
            }
        }

        print(init, 0);
        System.out.println(sb);
    }

    public static void print(Word word, int cnt) {
        if(!word.s.equals("-")) {
            String depth =  "-".repeat((cnt - 1) * 2);
            sb.append(depth).append(word.s).append("\n");
        }

        for(Word next : word.next) {
            print(next, cnt + 1);
        }
    }
}

class Word {
    String s;
    Set<Word> next;

    public Word(String s) {
        this.s = s;
        next = new TreeSet<>(Comparator.comparing(w -> w.s));
    }
}
