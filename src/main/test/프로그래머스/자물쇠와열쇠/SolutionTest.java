package 프로그래머스.자물쇠와열쇠;


import main.java.프로그래머스.자물쇠와열쇠.Solution;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        int key[][] = {{0, 0}, {1, 0}};
        int lock[][] = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        boolean res = solution.solution(key,lock);
        System.out.println(res);
    }
}