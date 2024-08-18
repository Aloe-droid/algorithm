import java.util.*;

class Solution {
    static List<Boolean> inOutList;
    static List<String> idList;
    static Map<String, String> nameMap;
    public String[] solution(String[] record) {
        inOutList = new ArrayList<>();
        idList = new ArrayList<>();
        nameMap = new HashMap<>();
        
        for(String s : record){
            StringTokenizer st = new StringTokenizer(s);
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            if(s1.equals("Enter")){
                String s3 = st.nextToken();
                enter(s2, s3);
            }else if(s1.equals("Change")){
                String s3 = st.nextToken();
                update(s2, s3);
            }else{
                delete(s2);
            }
        }
        
        String[] answer = new String[idList.size()];
        for(int i = 0; i < idList.size(); i++){
            String id = idList.get(i);
            String nn = nameMap.get(id);
            boolean io = inOutList.get(i);
            
            if(io) answer[i] = nn + "님이 들어왔습니다.";
            else answer[i] = nn + "님이 나갔습니다.";
        }
        
        return answer;
    }
    
    public static void enter(String id, String name){
        inOutList.add(true);
        idList.add(id);
        nameMap.put(id, name);
    }
    
    public static void update(String id, String name){
        nameMap.put(id, name);
    }
    
    public static void delete(String id){
        inOutList.add(false);
        idList.add(id);
    }
}