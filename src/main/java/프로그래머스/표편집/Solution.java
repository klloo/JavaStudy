package main.java.프로그래머스.표편집;

import java.util.*;

class Node {
    Node up;
    Node down;
    boolean isDeleted;
}
public class Solution {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        // 연결리스트 생성
        List<Node> nodeList = new ArrayList<>();
        for(int i=0; i<n; i++)
            nodeList.add(new Node());
        for(int i=1; i<n; i++) {
            nodeList.get(i-1).down = nodeList.get(i);
            nodeList.get(i).up = nodeList.get(i-1);
        }

        // 현재 노드
        Node curNode = nodeList.get(k);
        // 삭제된 노드 관리할 스택
        Stack<Node> recentlyDeleted = new Stack<>();

        for(String command:cmd) {
            // 명령어 분석
            String[] splitCmd = command.split(" ");
            if(splitCmd.length >= 2) {
                String dir = splitCmd[0];
                int step = Integer.parseInt(splitCmd[1]);
                // dir 방향으로 step만큼 움직여서 현재 노드설정
                if(dir.equals("D")) {
                    for(int i=0; i<step; i++)
                        curNode = curNode.down;
                } else {
                    for(int i=0; i<step; i++)
                        curNode = curNode.up;
                }
            }
            else if(splitCmd[0].equals("C")) {
                // 현재 노드 삭제 (현재 노드의 위아래 노드 연결)
                curNode.isDeleted = true;
                recentlyDeleted.push(curNode);
                Node upNode = curNode.up;
                Node downNode = curNode.down;
                if(upNode != null)
                    upNode.down = downNode;
                if(downNode != null) {
                    downNode.up = upNode;
                    curNode = downNode;
                } else {
                    curNode = upNode;
                }
            }
            else if(splitCmd[0].equals("Z")) {
                // 노드 복구 (복구할 노드의 위아래 노드에 복구할 노드 연결)
                Node addNode = recentlyDeleted.pop();
                addNode.isDeleted = false;
                Node upNode = addNode.up;
                Node downNode = addNode.down;
                if(upNode != null)
                    upNode.down = addNode;
                if(downNode != null)
                    downNode.up = addNode;
            }
        }
        // 삭제 됐으면 X, 아니면 O 붙이기
        StringBuilder sb = new StringBuilder();
        for(Node node : nodeList) {
            if(node.isDeleted)
                sb.append("X");
            else
                sb.append("O");
        }
        answer = sb.toString();
        return answer;
    }
}
