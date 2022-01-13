package main.java.프로그래머스.합승택시요금;

import java.util.*;

class Pair implements Comparable<Pair>{
    int v;
    int w;
    public Pair(int v,int w) {
        this.v = v;
        this.w = w;
    }

    // 우선순위 큐 사용하기 위해 Comparable 인터페이스 구현
    @Override
    public int compareTo(Pair o) {
        return this.w - o.w;
    }
}
public class Solution {
    int[][] graph;
    int[] dist;
    int INF = Integer.MAX_VALUE;
    int n;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;
        this.n = n;
        graph = new int[n+1][n+1];
        dist = new int[n+1];

        // 그래프 생성. n이 200이라 배열로 생성해도 될 것 같았다.
        for(int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int w = fare[2];
            graph[u][v] = w;
            graph[v][u] = w;
        }

        // 거리 구하기
        int[] shareDist = dijkstra(s);
        int[] aDist = dijkstra(a);
        int[] bDist = dijkstra(b);

        // 1부터 n까지 노드 하나씩 차례로 합승 도착 지점으로 정하고 최단 거리 계산
        for(int i=1; i<=n; i++) {
            int cost = shareDist[i] + aDist[i] + bDist[i];
            answer = cost > answer ? answer : cost;
        }
        return answer;
    }
    int[] dijkstra(int s) {
        dist = new int[n+1];
        Arrays.fill(dist, INF);

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(s,0));
        dist[s] = 0;

        while(!pq.isEmpty()) {
            Pair cur = pq.poll();
            int curv = cur.v;
            int curw = cur.w;
            if(curw > dist[curv]) continue;
            int[] curEList = graph[curv];
            int len = curEList.length;

            for(int i=1; i<len; i++) {
                if(curEList[i] == 0) continue;
                int nextv = i;
                int nextw = curEList[i];
                if(dist[nextv] > curw + nextw) {
                    pq.add(new Pair(nextv,curw + nextw));
                    dist[nextv] = curw + nextw;
                }
            }
        }
        return dist;
    }
}