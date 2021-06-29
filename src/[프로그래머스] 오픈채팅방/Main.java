package com.huiy;

public class Main {

    public static void main(String[] args) {
        com.huiy.OpenChatting oc = new com.huiy.OpenChatting();
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        String [] ans = oc.solution(record);
        for(String a:ans) {
            System.out.println(a);
        }
    }
}
