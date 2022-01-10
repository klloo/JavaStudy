package main.java.프로그래머스.메뉴리뉴얼;

import java.util.*;

public class Solution {
    // 키 : 메뉴 조합 , 값 : 해당 메뉴 조합이 등장한 갯수
    Map<String,Integer> combMap = new HashMap<>();
    StringBuilder comb = new StringBuilder();

    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        // 주문에서 가능한 모든 조합 생성
        for(int i=0;i<orders.length;i++)
            dfs(orders[i],0);

        Set<String> keys = combMap.keySet();
        List<String> answerList = new ArrayList<>();

        // 코스 갯수 확인
        for(int num : course) {
            int max = 0;
            for(String key : keys) {
                int cnt = combMap.get(key);
                // 만약 키에 해당하는 조합의 갯수가 2미만이거나, 키의 길이가 코스요리에서 원한 숫자가 아니면 확인 X
                if(key.length() != num || cnt < 2) continue;
                // 최댓값 갱신
                max = max > cnt ? max : cnt;
            }
            for(String key : keys) {
                // 키의 길이가 코스에서 원하는 숫자가 아니면 확인X
                if(key.length() != num) continue;
                // 최댓값에 해당하는 메뉴 조합 정답 리스트에 넣어주기
                int cnt = combMap.get(key);
                if(cnt == max) {
                    answerList.add(key);
                }
            }
        }
        // 정답 배열 생성
        answer = new String[answerList.size()];
        int idx = 0;
        for(String str: answerList)
            answer[idx++] = str;
        Arrays.sort(answer);
        return answer;
    }

    void dfs(String str, int idx) {
        // 만약 메뉴를 끝까지 확인했다면
        if(idx == str.length()) {
            // 메뉴는 조합이므로 순서 상관없이 보기 위해 오름차순으로 알파벳 정렬 후 맵에 넣어주기
            int cnt = 1;
            char[] combArr = comb.toString().toCharArray();
            Arrays.sort(combArr);
            String combStr = new String(combArr);
            if (combMap.containsKey(combStr)) {
                cnt = combMap.get(combStr) + 1;
            }
            combMap.put(combStr, cnt);
            return;
        }
        char curChar = str.charAt(idx);
        // 현재 알파벳을 포함하는 경우
        comb.append(curChar);
        dfs(str, idx+1);
        // 현재 알파벳을 포함하지 않는 경우
        comb.deleteCharAt(comb.length()-1);
        dfs(str, idx+1);
    }
}
