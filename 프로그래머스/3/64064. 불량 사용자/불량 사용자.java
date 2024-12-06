import java.util.*;

class Solution {
    static Set<Set<String>> ans = new HashSet<>();
    static Set<String> hs = new HashSet<>();
    static String[] users, bannedUsers;
    public int solution(String[] user_id, String[] banned_id) {
        users = user_id;
        bannedUsers = banned_id;
        dfs(0);
        return ans.size();
    }
    
    public static void dfs(int k){
        if(k == bannedUsers.length){
            if(hs.size() == k){
                ans.add(new HashSet<>(hs));
            }
            return;
        }
        
        String banUser = bannedUsers[k];
        for(String user : users){
            if(check(banUser, user) && !hs.contains(user)){
                hs.add(user);
                dfs(k + 1);
                hs.remove(user);
            }
        }
        
        dfs(k + 1);
    }
    
    public static boolean check(String s1, String s2){
        if(s1.length() != s2.length()) return false;
        
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i) && s1.charAt(i) != '*') return false;
        }
        
        return true;
    }
}