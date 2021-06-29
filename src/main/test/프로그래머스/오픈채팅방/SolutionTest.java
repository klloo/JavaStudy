package main.test.프로그래머스.오픈채팅방;
import main.java.프로그래머스.오픈채팅방.Solution;
import org.junit.Test;

public class SolutionTest {

    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] ans = solution.solution(record);
        for (String a : ans) {
            System.out.println(a);
        }
    }
}