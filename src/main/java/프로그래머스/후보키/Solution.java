package main.java.프로그래머스.후보키;

import java.util.*;

public class Solution {
    private ArrayList<HashSet<Integer>> candidateKey;
    public int solution(String[][] relation) {
        int answer = 0;
        candidateKey = new ArrayList<HashSet<Integer>>();
        HashSet<Integer> key = new HashSet<Integer>();
        int len = relation[0].length;
        for(int i=1;i<=len;i++)
            this.dfs(key,0,i,relation);
        answer = candidateKey.size();
        return answer;
    }

    public void dfs(HashSet<Integer> key,int cur,int size,String[][] relation) {
        if(cur>relation[0].length)
            return;

        if (key.size() == size) {
            //최소성 검사
            for (HashSet<Integer> set : candidateKey) {
                if (key.containsAll(set))
                    return;
            }
            //유일성 검사
            if (isKey(key, relation)) {
                HashSet<Integer> newKey = new HashSet<Integer>(key);
                candidateKey.add(newKey);
            }
            return;
        }
        key.add(cur);
        dfs(key,cur+1,size,relation);
        key.remove(cur);
        dfs(key,cur+1,size,relation);
    }

    public boolean isKey(HashSet<Integer> key, String[][] relation) {
        int r = relation.length;
        HashSet<ArrayList<String>> setList = new HashSet<ArrayList<String>>();
        for(int i=0;i<r;i++) {
            ArrayList<String> list = new ArrayList<String>();
            for(int k:key)
                list.add(relation[i][k]);
            setList.add(list);
            if(setList.size() < i + 1)
                return false;
        }
        return true;
    }
}
