package 프로그래머스.기둥과보설치;


import main.java.프로그래머스.기둥과보설치.Solution;
import org.junit.Test;

public class SolutionTest{
    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        int n = 5;
        int[][] build_frame = {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}};
        int[][] ans = solution.solution(n,build_frame);
        for(int i=0;i<ans.length;i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(ans[i][j]+" ");
            }
            System.out.println();
        }
    }
}