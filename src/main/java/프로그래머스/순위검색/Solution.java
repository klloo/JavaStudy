package main.java.프로그래머스.순위검색;

import java.util.*;

public class Solution {
    Map<String,List<Integer>> combMap = new HashMap<>();
    List<String> comb = new ArrayList<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = {};
        // 지원자 정보로 맵 생성
        // 키 : 지원자 정보의 조합, 값: 해당 조합을 가진 지원자의 점수 목록
        for(String i : info) {
            String[] parseInfo = i.split(" ");
            comb = new ArrayList<>();
            dfs(parseInfo, 0);
        }
        // 점수 정렬
        Set<String> keys = combMap.keySet();
        for(String key : keys) {
            Collections.sort(combMap.get(key));
        }
        answer = new int[query.length];
        int idx = 0;
        for(String q : query) {
            // 쿼리 스트링 가공. and랑 - 없애고 " " 기준으로 split하면 [0]에는 정보 조합, [1]에는 기준 점수 들어
            q = q.replaceAll(" and ","");
            q = q.replaceAll("-","");
            String[] parseQuery = q.split(" ");
            String key = parseQuery[0];
            int value = Integer.parseInt(parseQuery[1]);
            int ans = 0;
            // 해당 조합이 존재하면 키값으로 점수 목록 꺼내서, 기준 점수 이상인 점수가 몇 명인지 이분탐색
            if(combMap.containsKey(key)) {
                List<Integer> valueList = combMap.get(key);
                ans = binarySearch(0,valueList.size()-1,value,valueList);
            }
            answer[idx++] = ans;
        }
        return answer;
    }

    // value에 해당하는 점수가 몇 번째 위치하는 점수인지 이분탐색
    int binarySearch(int s, int e, int value, List<Integer> valueList) {
        // s가 e를 넘어서는 순간, s의 위치는 value보다 큰 값 중 가장 작은 값의 위치이다.
        if (s > e)
            return valueList.size()-s;
        int m = (s+e)/2;
        // 찾으려는 값이 더 크면 오른쪽 탐색
        if(valueList.get(m) < value)
            return binarySearch(m+1,e,value,valueList);
        else
            return binarySearch(s,m-1,value,valueList);
    }

    void dfs(String[] info, int idx) {
        int lastIdx = info.length-1;
        if(idx == lastIdx) {
            StringBuilder sb = new StringBuilder();
            for(String c : comb) {
                sb.append(c);
            }
            String key = sb.toString();
            List<Integer> values = new ArrayList<>();
            if(combMap.containsKey(key)) {
                values = combMap.get(key);
            }
            values.add(Integer.parseInt(info[idx]));
            combMap.put(key, values);
            return;
        }
        // 현재 정보를 조합에 넣는 경우
        comb.add(info[idx]);
        dfs(info,idx+1);
        // 현재 정보를 조합에 넣지 않는 경우
        comb.remove(comb.size()-1);
        dfs(info,idx+1);
    }
}
