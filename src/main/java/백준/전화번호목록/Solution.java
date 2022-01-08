package main.java.백준.전화번호목록;

import java.util.*;

class TrieNode {
    TrieNode[] child = new TrieNode[10]; // 0~9 까지 저장
    boolean isLeaf = true; // 리프노드 여부
    char val; // 현재 노드의 값;

    public TrieNode(char val) {
        this.val = val;
    }

    void insert(String str, int idx) {
        // 문자열을 끝까지 확인했다면 종료
        if (str.length() == idx) {
            return;
        }
        char curChar = str.charAt(idx);
        int curCharIdx = str.charAt(idx) - '0';
        // 이번에 저장할 문자 인덱스에 해당하는 노드가 null이라면 새로 생성
        if (this.child[curCharIdx] == null || this.isLeaf) {
            this.child[curCharIdx] = new TrieNode(curChar);
            this.isLeaf = false;
        }
        // 다음 문자 저장
        this.child[curCharIdx].insert(str, idx + 1);
    }

    boolean isConsistency(String str, int idx) {
        // 문자열을 끝까지 확인했다면 결과 리턴
        if (str.length() == idx) {
            // 끝까지 봤는데 그게 리프노드면 일관성 있는거고 아니면 없는거
            return isLeaf;
        }
        // 다음 문자 확인
        int curCharIdx = str.charAt(idx) - '0';
        if (!this.isLeaf) {
            if (this.child[curCharIdx] != null)
                return this.child[curCharIdx].isConsistency(str, idx + 1);
        }
        return false;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode(' ');
    }

    void insert(String str) {
        root.insert(str, 0);
    }

    boolean isConsistency(String str) {
        return root.isConsistency(str, 0);
    }
}

public class Solution {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- != 0) {
            int n = sc.nextInt();
            sc.nextLine();

            Trie trie = new Trie();
            List<String> numList = new ArrayList<>();
            for(int i=0;i<n;i++) {
                String str = sc.nextLine();
                numList.add(str);
                trie.insert(str);
            }
            boolean res = true;
            for(int i=0;i<n;i++) {
                if(!trie.isConsistency(numList.get(i))) {
                    res = false;
                    break;
                }
            }
            System.out.println(res?"YES":"NO");
        }
    }
}
