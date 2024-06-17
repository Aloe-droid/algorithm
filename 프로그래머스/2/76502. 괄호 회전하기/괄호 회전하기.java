import java.util.*;

class Solution {
    public int solution(String s) {
        int ans = 0;
        for(int i = 0; i < s.length(); i++){
            Stack<Character> st = new Stack();
            boolean flag = true;
            
            for(int j = i; j < i + s.length(); j++){
                char c = s.charAt(j % s.length());
                if(c == '{' || c == '[' || c == '(') st.add(c);
                else{
                    if(c == '}' && !st.isEmpty() && st.peek() == '{') st.pop();
                    else if(c == ']' && !st.isEmpty() && st.peek() == '[') st.pop();
                    else if(c == ')' && !st.isEmpty() && st.peek() == '(') st.pop();
                    else{
                        flag = false;
                        break;
                    }
                }
            }
            if(flag && st.isEmpty()) ans++;
        }
        return ans;
    }
}