package main.java.프로그래머스.행렬테두리회전하기;

public class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};
        answer = new int[queries.length];
        int[][] board = new int[rows+1][columns+1];
        int num = 1;
        for(int i=1;i<=rows;i++) {
            for (int j = 1; j <= columns; j++)
                board[i][j] = num++;
        }

        int idx = 0;
        for(int[] query:queries) {
            int x = query[0];
            int starty = query[1];
            int endy = query[3];
            int min = 10001;
            int[] t = new int[3];
            t[0] = board[x][endy];
            for(int y = endy; y > starty; y--)
                board[x][y] = board[x][y-1];


            int y = query[3];
            int startx = query[0] + 1;
            int endx = query[2];
            t[1] = board[endx][y];
            for(x = endx; x > startx; x--)
                board[x][y] = board[x-1][y];
            board[startx][y] = t[0];

            x = query[2];
            starty = query[1];
            endy = query[3] - 1;
            t[2] = board[x][starty];
            for(y = starty; y < endy; y++)
                board[x][y] = board[x][y+1];
            board[x][endy] = t[1];

            y = query[1];
            startx = query[0];
            endx = query[2] - 1;
            for(x = startx; x < endx; x++)
                board[x][y] = board[x+1][y];
            board[endx][y] = t[2];


            for(y = query[1]; y<=query[3]; y++) {
                x = query[0];
                min = min > board[x][y] ? board[x][y] : min;
                x = query[2];
                min = min > board[x][y] ? board[x][y] : min;
            }
            for(x = query[0]; x<=query[2]; x++) {
                y = query[1];
                min = min > board[x][y] ? board[x][y] : min;
                y = query[3];
                min = min > board[x][y] ? board[x][y] : min;
            }

            answer[idx++] = min;
        }




        return answer;
    }
}
