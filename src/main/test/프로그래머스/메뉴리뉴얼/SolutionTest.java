package 프로그래머스.메뉴리뉴얼;

import main.java.프로그래머스.메뉴리뉴얼.Solution;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2,3,4};
        String[] result = solution.solution(orders,course);
        for(String res : result)
            System.out.print(res+" ");
    }
}