package 프로그래머스.가사검색;


import main.java.프로그래머스.가사검색.Solution;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        int[] result = solution.solution(words,queries);
        for(int res : result)
            System.out.print(res+" ");
    }
}