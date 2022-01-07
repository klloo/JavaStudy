package 프로그래머스.외벽점검;

import main.java.프로그래머스.외벽점검.Solution;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        int n = 12;
        int[] weak = {1,5,6,10};
        int[] dist = {1,2,3,4};
        int res = solution.solution(n,weak,dist);
        System.out.println(res);
    }

}