import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Member[] members = new Member[N];
        
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            members[i] = new Member(age, name);    
        }

        Arrays.sort(members, Comparator.comparingInt(m -> m.age));
        StringBuilder sb = new StringBuilder();
        for(Member m : members) sb.append(m);
        System.out.println(sb);
    }
}

class Member {
    int age;
    String name;

    public Member(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return age + " " + name + "\n";
    }
}