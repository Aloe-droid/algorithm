class Solution {
    public int solution(String myString, String pat) {
        char[] chars = myString.toCharArray();
        for(int i = 0 ; i < chars.length; i++){
            if(chars[i] == 'A') chars[i] = 'B';
            else chars[i] = 'A';
        }
        String s = new String(chars);
        if(s.contains(pat)) return 1;
        else return 0;
    }
}