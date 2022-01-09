package main.java.프로그래머스.가사검색;

import java.util.*;

class Trie {
    Trie[] child = new Trie[26];
    // 자식 노드의 수
    int cnt = 0;

    void insert(String str, int idx) {
        // 문자열을 끝까지 다 확인했다면 리턴
        if(idx == str.length())
            return;
        // 알파벳으로 삽입해야할 인덱스 구하기
        int curIdx = str.charAt(idx) - 'a';
        // 해당 child가 null 이면 새로 할당
        if(child[curIdx] == null)
            this.child[curIdx] = new Trie();
        // 자식 수 늘려주고 남은 문자열 추가
        this.cnt++;
        this.child[curIdx].insert(str,idx+1);
    }
    int getCount(String query,int idx) {
        // 현재 확인하고 있는 문자가 ?라면 현재 노드의 자손 수 리턴
        char curChar = query.charAt(idx);
        if(curChar == '?')
            return this.cnt;
        // 다음 문자 확인
        int curIdx = query.charAt(idx) - 'a';
        if(this.child[curIdx] != null)
            return this.child[curIdx].getCount(query,idx+1);
        // 다음 문자가 물음표도 아닌데 없는거면 없는 문자열이므로 0 리턴
        return 0;
    }
}
public class Solution {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = {};
        // 트라이 맵 두 개 생성
        // 키 : 문자열의 길이 / 값 : 트라이
        Map<Integer,Trie> trieMap = new HashMap<>();
        Map<Integer,Trie> rTrieMap = new HashMap<>();

        // 길이 별로 다른 트라이에 문자열 삽입
        for(String word : words) {
            Trie trie = new Trie();
            int len = word.length();
            if(trieMap.containsKey(len))
                trie = trieMap.get(len);
            trie.insert(word, 0);
            trieMap.put(len,trie);

            // 물음표가 첫글자에 오는 쿼리 대비해서 문자열 뒤집은 트라이도 생성
            String rword = new StringBuilder(word).reverse().toString();
            Trie rTrie = new Trie();
            if(rTrieMap.containsKey(len))
                rTrie = rTrieMap.get(len);
            rTrie.insert(rword, 0);
            rTrieMap.put(len,rTrie);
        }
        answer = new int[queries.length];
        int idx = 0;
        for(String query : queries) {
            int len = query.length();
            int res = 0;
            // ?로 시작하는 쿼리는 뒤집어진 트라이에서 검색
            if(query.charAt(0) == '?') {
                String rQuery = new StringBuilder(query).reverse().toString();
                // 쿼리의 길이에 해당하는 트라이 꺼내서 검색
                if(rTrieMap.containsKey(len))
                    res = rTrieMap.get(len).getCount(rQuery, 0);
                answer[idx++] = res;
            }
            else {
                if(trieMap.containsKey(len))
                    res = trieMap.get(len).getCount(query, 0);
                answer[idx++] = res;
            }
        }
        return answer;
    }
}
