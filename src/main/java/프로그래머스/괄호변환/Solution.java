package main.java.프로그래머스.괄호변환;

import java.util.*;

public class Solution {
    public String solution(String p) {
        String answer = "";
        // 1. 입력이 빈 문자열인 경우 빈 문자열 반환
        answer = p.length() == 0? "" : getPalindrome(p);
        return answer;
    }
    String getPalindrome(String p) {
        char[] strArr = p.toCharArray();
        int len = strArr.length;

        // 2. 균형잡힌 괄호 문자열 구하기
        int left = 0, right = 0;
        int idx;
        for (idx = 0; idx < len; idx++) {
            if('(' == strArr[idx]) left++;
            if(')' == strArr[idx]) right++;
            if(left == right)
                break;
        }

        String u = p.substring(0, idx + 1);
        // v는 빈문자열 일 수 도 있으니 예외 처리
        String v = "";
        if(idx + 1 <=  len)
            v = p.substring(idx + 1, len);

        // 3. 만약 u가 균형잡힌 괄호 문자열이라면 v도 균형잡아서 붙여주고 리턴
        String palindromeV = v.length()!=0?getPalindrome(v):"";
        if(isPalindrome(u)) {
            return u + palindromeV;
        }

        // 4. 아니라면
        // 4-1. 빈 문자열에 ( 붙이고 v 균형 잡아서 붙이고 ) 붙여주기
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(palindromeV);
        sb.append(")");

        // 4-2. u의 첫번째와 마지막 문자 제거하고, 나머지 문자열의 괄호 방향 뒤집어서 뒤에 붙이기
        char[] uArr = u.substring(1,idx).toCharArray();
        for(char c : uArr)
            sb.append(c==')'?"(":")");

        // 4-3. 생성된 문자열 반환
        return sb.toString();
    }
    // 올바른 문자열인지 확인하는 메소드
    boolean isPalindrome(String p) {
        Stack<Character> stack = new Stack<>();
        char[] strArr = p.toCharArray();
        for(char c : strArr) {
            if(c == '(')
                stack.push(c);
            else {
                if(stack.isEmpty())
                    return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
