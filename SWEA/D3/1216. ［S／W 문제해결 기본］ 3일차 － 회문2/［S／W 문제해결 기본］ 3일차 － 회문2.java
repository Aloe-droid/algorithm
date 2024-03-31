import java.io.*;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int len = 100;

        for (int tc = 1; tc <= 10; tc++) {
            int T = Integer.parseInt(br.readLine());
            char[][] chars = new char[len][len];
            for (int i = 0; i < len; i++) {
                String s = br.readLine();
                for (int j = 0; j < len; j++) {
                    chars[i][j] = s.charAt(j);
                }
            }

            int maxLen = 0;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    for (int k = j; k < len; k++) {
                        if (k + 1 - j < maxLen) continue;

                        char[] temp1 = new char[k + 1 - j];
                        char[] temp2 = new char[k + 1 - j];
                        for (int l = 0; l < temp1.length; l++) temp1[l] = chars[i][j + l];
                        for (int l = 0; l < temp2.length; l++) temp2[l] = chars[j + l][i];
                        String s1 = String.valueOf(temp1);
                        String s2 = String.valueOf(temp2);

                        if (isPal(s1)) maxLen = s1.length();
                        if (isPal(s2)) maxLen = s2.length();
                    }
                }
            }
            bw.write("#" + T + " " + maxLen + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean isPal(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) return false;
        }
        return true;
    }
}