package main.java.프로그래머스.다단계칫솔판매;

import java.util.*;

class Node {
    int idx;
    String name;
    ArrayList<Node> childnodes;
    int profit;
    public Node(int idx,String name) {
        this.idx = idx;
        this.name = name;
        childnodes = new ArrayList<Node>();
        profit = 0;
    }
    public void insert(String parent,Node child) {
        if(parent.equals(name))
            childnodes.add(child);
        if(childnodes.size()>0) {
            childnodes.forEach(node -> node.insert(parent, child));
        }
    }
    public int calculateProfit(String seller,int amount) {
        if((int)(amount*100*0.1)==0)
            return 0;
        if(name.equals(seller)) {
            profit += (int) (amount*100*0.9);
            return (int) (amount*100*0.1);
        }
        int t = 0;
        for(Node child:childnodes) {
            t += child.calculateProfit(seller,amount);
        }
        int ret = (int)(t*0.1);
        profit += t-((int)(t*0.1));
        return ret;
    }
    public void result(Map<String,Integer> resMap) {
        resMap.put(name,profit);
        if(childnodes.size()>0) {
            childnodes.forEach(node -> node.result(resMap));
        }
    }
}
public class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};

        Node root = new Node(-1,"-");

        int len = enroll.length;
        for(int i=0;i<len;i++) {
            Node child = new Node(i,enroll[i]);
            root.insert(referral[i],child);
        }

        len = seller.length;
        for(int i=0;i<len;i++)
            root.calculateProfit(seller[i],amount[i]);

        Map<String,Integer> resMap = new HashMap<String,Integer>();
        root.result(resMap);

        answer = new int[enroll.length];
        len = enroll.length;
        for(int i=0;i<len;i++)
            answer[i] = resMap.get(enroll[i]);

        return answer;
    }
}
