package main.java.프로그래머스.순위검색;

import java.util.*;

public class Solution {
    Map<String,List<Integer>> combMap = new HashMap<>();
    List<String> comb = new ArrayList<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = {};
        for(String i : info) {
            String[] parseInfo = i.split(" ");
            comb = new ArrayList<>();
            dfs(parseInfo, 0);
        }
        Set<String> keys = combMap.keySet();
        for(String key : keys) {
            Collections.sort(combMap.get(key));
        }
        answer = new int[query.length];
        int idx = 0;
        for(String q : query) {
            q = q.replaceAll(" and ","");
            q = q.replaceAll("-","");
            String[] parseQuery = q.split(" ");
            String key = parseQuery[0];
            int value = Integer.parseInt(parseQuery[1]);
            int ans = 0;
            if(combMap.containsKey(key)) {
                List<Integer> valueList = combMap.get(key);
                ans = binarySearch(0,valueList.size()-1,value,valueList);
            }
            answer[idx++] = ans;
        }
        return answer;
    }

    int binarySearch(int s, int e, int value, List<Integer> valueList) {
        while(s <= e) {
            int m = (s + e)/2;
            if(valueList.get(m) < value) {
                s = m+1;
            }else {
                e = m-1;
            }
        }
        return valueList.size()-s;
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
        comb.add(info[idx]);
        dfs(info,idx+1);
        comb.remove(comb.size()-1);
        dfs(info,idx+1);
    }
}
