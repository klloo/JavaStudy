package com.huiy;

import java.util.*;

class OpenChatting {
    public String[] solution(String[] record) {
        String[] answer = {};
        ArrayList<String> result = new ArrayList<>();
        //key : uid , value : nickname
        Map<String,String> userMap = new HashMap<>();
        for(String r : record) {
            //공백 " "을 기준으로 문자열 파싱
            String[] order = r.split(" ");
            //유저가 입장하면 맵에 user정보 put하고 uid님이 들어왔다는 메세지 넣어줌
            if(order[0].equals("Enter")) {
                userMap.put(order[1],order[2]);
                result.add(order[1]+"님이 들어왔습니다.");
            }
            //닉네임 바꾸면 user정보 갱신
            else if(order[0].equals("Change"))
                userMap.put(order[1],order[2]);
            //니기면 나간다고 해주기
            else {
                result.add(order[1]+"님이 나갔습니다.");
            }
        }
        //uid를 nickname으로 replace
        answer = new String[result.size()];
        int i = 0;
        for(String res:result) {
            int idx = res.indexOf("님");
            String uid = res.substring(0,idx);
            String nickName = userMap.get(uid);
            res = res.replace(uid,nickName);
            answer[i++] = res;
        }
        return answer;
    }
}