package main.test.프로그래머스.순위검색;

import main.java.프로그래머스.순위검색.Solution;
import org.junit.Test;

public class SolutionTest {

    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        int[] result = solution.solution(info,query);
        for(int res : result)
            System.out.print(res+" ");
    }
}