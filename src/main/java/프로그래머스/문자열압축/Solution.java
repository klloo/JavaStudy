package main.java.프로그래머스.문자열압축;

public class Solution {
    public int solution(String s) {
        int answer = s.length();
        int len = s.length();
        // 최대 절반으로 압축할 수 있으므로 len/2까지 반복한다.
        for(int unit=1; unit<=len/2; unit++) {
            StringBuilder sb = new StringBuilder();
            // 처음 기준 string은 0부터 unit까지 부분 문자열이다
            String baseStr = s.substring(0, unit);
            int cnt = 1;
            // 다음 문자열을 unit만큼 잘라서 확인한다.
            for(int k=unit; k<len; k+=unit) {
                if (k + unit > len) continue;
                String nextStr = s.substring(k, k + unit);
                // 만약 기준 문자열과 다음 문자열이 같다면 반복되는 횟수를 증가시켜준다.
                if (baseStr.equals(nextStr))
                    cnt++;
                else {
                    // 기준 문자열과 다음 문자열이 다르다면 반복횟수와 기준 문자열을 더해준다
                    // 반복횟수를 초기화하고, 반복이 끝났으니 다음문자열을 기준으로 남은 문자열을 압축할 수 있는지 확인한다.
                    sb.append(cnt > 1 ? Integer.toString(cnt) : "");
                    sb.append(baseStr);
                    baseStr = nextStr;
                    cnt = 1;
                }
            }
            // 마지막 기준 문자열을 붙여준다.
            sb.append(cnt > 1 ? Integer.toString(cnt) : "");
            sb.append(baseStr);
            // 문자열을 unit 단위로 자르고 남은 문자열을 붙여준다.
            if(len%unit > 0) {
                String remainStr = s.substring((len - len % unit), len);
                sb.append(remainStr);
            }
            int resLen = sb.length();
            answer = answer > resLen ? resLen : answer;
        }

        return answer;
    }
}
