package main.java.프로그래머스.길찾기게임;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Node implements Comparable<Node>{
    private int nodeNo;
    private int x,y;
    private Node rnode;
    private Node lnode;
    public Node(int nodeNo, int x, int y) {
        this.nodeNo = nodeNo;
        this.x = x;
        this.y = y;
        this.rnode = null;
        this.lnode = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setRnode(Node rnode) {
        this.rnode = rnode;
    }

    public void setLnode(Node lnode) {
        this.lnode = lnode;
    }

    public void insert(Node newNode) {
        //왼쪽 자식
        if(newNode.getX()<this.x) {
            if(this.lnode != null)
                this.lnode.insert(newNode);
            else
                this.lnode = newNode;
        }
        //오른쪽 자식
        else {
            if(this.rnode != null)
                this.rnode.insert(newNode);
            else
                this.rnode = newNode;
        }
    }

    public void preorder(ArrayList<Integer> preorderList) {
        preorderList.add(this.nodeNo);
        if(this.lnode!=null)
            this.lnode.preorder(preorderList);
        if(this.rnode!=null)
            this.rnode.preorder(preorderList);
    }
    public void postorder(ArrayList<Integer> postorderList) {
        if(this.lnode!=null)
            this.lnode.postorder(postorderList);
        if(this.rnode!=null)
            this.rnode.postorder(postorderList);
        postorderList.add(this.nodeNo);
    }

    @Override
    public int compareTo(Node o) {
        //양수를 리턴하면 자리이동 O
        return o.getY() - this.y;
    }
}
public class Solution {
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        ArrayList<Node> nodeList = new ArrayList<Node>();

        int i=1;
        for(int[] node:nodeinfo) {
            Node newNode = new Node(i++,node[0],node[1]);
            nodeList.add(newNode);
        }

        Collections.sort(nodeList);
        Node root = nodeList.get(0);
        int len = nodeList.size();
        for(i=1;i<len;i++)
            root.insert(nodeList.get(i));

        ArrayList<Integer> preorderList = new ArrayList<Integer>();
        ArrayList<Integer> postorderList = new ArrayList<Integer>();
        root.preorder(preorderList);
        root.postorder(postorderList);

        answer = new int[2][len];
        for (i=0;i<len;i++) {
            answer[0][i] = preorderList.get(i);
            answer[1][i] = postorderList.get(i);
        }

        return answer;
    }
}
