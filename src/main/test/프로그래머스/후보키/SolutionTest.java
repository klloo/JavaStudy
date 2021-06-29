package main.test.프로그래머스.후보키;

import main.java.프로그래머스.후보키.Solution;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        String[][] record = {{"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}};
        int ans = solution.solution(record);
        assertEquals(ans,2);
    }
}