package main.java;

import main.java.프로그래머스.오픈채팅방.Solution;

public class Main {

    public static void main(String[] args) {
        Solution oc = new Solution();
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        String [] ans = oc.solution(record);
        for(String a:ans) {
            System.out.println(a);
        }
    }
}
