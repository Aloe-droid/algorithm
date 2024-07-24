class Solution {
    public String solution(String myString) {
        char[] c = myString.toCharArray();
        for(int i = 0; i < c.length; i++){
            if(c[i] == 'a') c[i] = 'A';
            else if(c[i] > 'A' && c[i] <= 'Z') c[i] += 32;
        }
        return new String(c);
    }
}