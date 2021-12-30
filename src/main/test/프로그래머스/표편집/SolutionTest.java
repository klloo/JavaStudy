package 프로그래머스.표편집;

import main.java.프로그래머스.표편집.Solution;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        String res = solution.solution(n,k,cmd);
        System.out.println(res);
    }
}