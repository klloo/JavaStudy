package 프로그래머스.괄호변환;


import main.java.프로그래머스.괄호변환.Solution;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        String p = "()))((()";
        System.out.println(solution.solution(p));
    }
}