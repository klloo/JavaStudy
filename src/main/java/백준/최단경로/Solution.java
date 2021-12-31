package main.java.백준.최단경로;

import java.util.*;

class Pair implements Comparable<Pair>{
    int vertex;
    int weight;
    public Pair(int vertex,int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
    @Override
    public int compareTo(Pair pair) {
        return this.weight > pair.weight ? 1 : - 1;
    }

}
public class Solution {
    static int INF = 100000000;
    public static void main(String args[]) {
        // 입력
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        int k = sc.nextInt();
        Map<Integer,List<Pair>> graph = new HashMap<>();
        for(int i=0; i<E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            // 그래프 생성
            List<Pair> list = new ArrayList<Pair>();
            if(graph.containsKey(u))
                list = graph.get(u);
            list.add(new Pair(v,w));
            graph.put(u, list);
        }
        // dist 배열 무한대 값으로 초기화
        int[] dist = new int[20001];
        Arrays.fill(dist, INF);

        // 시작 점의 거리 0으로 설정하고 우선순위큐에 삽입
        dist[k] = 0;
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Pair(k,0));

        while(!priorityQueue.isEmpty()) {
            // 최소거리가 가장 작은 노드 선택
            Pair curv = priorityQueue.poll();
            List<Pair> adjacentList = graph.get(curv.vertex);
            if(adjacentList == null)
                continue;
            // 현재 노드에 인접한 노드들 확인
            for(Pair nextv : adjacentList) {
                // 현재 노드를 거쳐가는 비용이 더 적으면 갱신 후 우선순위 큐에 삽입
                if(dist[nextv.vertex] > dist[curv.vertex] + nextv.weight) {
                    dist[nextv.vertex] = dist[curv.vertex] + nextv.weight;
                    priorityQueue.add(new Pair(nextv.vertex, dist[nextv.vertex]));
                }
            }
        }
        for(int i=1;i<=V;i++) {
            String res = dist[i] == INF ? "INF" : Integer.toString(dist[i]);
            System.out.println(res);
        }
    }
}
