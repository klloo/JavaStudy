package main.java.프로그래머스.미로탈출;

import java.util.*;

// 간선 정보를 저장하는 클래스
class Edge {
    // 목적지 노드
    int vertex;
    // 목적지 노드 까지의 거리
    int weight;
    // 뒤집어진 간선인지 여부
    boolean isReverse;

    public Edge(int vertex, int weight, boolean isReverse) {
        this.vertex = vertex;
        this.weight = weight;
        this.isReverse = isReverse;
    }
}

// 우선순위 큐에 삽입되는 노드 클래스
class Node implements Comparable<Node> {
    // 현재 노드
    int vertex;
    // 시작 ~ 현재 노드의 경로
    int weight;
    // 현재 트랩의 활성 상태
    int trapStatus;

    public Node(int vertex, int weight, int trapStatus) {
        this.vertex = vertex;
        this.weight = weight;
        this.trapStatus = trapStatus;
    }

    @Override
    public int compareTo(Node node) {
        return this.weight > node.weight ? 1 : -1;
    }
}
public class Solution {
    static int INF = 100000000;
    Map<Integer, Integer> trapMap = new HashMap<>();

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;

        // 그래프 생성
        List<Edge>[] graph = new ArrayList[n+1];

        for(int i=1; i<=n; i++)
            graph[i] = new ArrayList<>();

        int len = roads.length;
        for(int i=0; i<len; i++) {
            int u = roads[i][0];
            int v = roads[i][1];
            int w = roads[i][2];
            graph[u].add(new Edge(v,w,false));
            graph[v].add(new Edge(u,w,true));
        }

        // 트랩 맵 생성
        len = traps.length;
        for(int i=0; i<len; i++)
            trapMap.put(traps[i],i);

        // dist 배열 생성
        int dist[][] = new int[n+1][1<<10];
        for(int i=1; i<=n; i++)
            Arrays.fill(dist[i],INF);

        // 우선순위 큐에 시작 노드 삽입
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(start,0,0));

        answer = INF;
        while(!priorityQueue.isEmpty()) {
            // 처리되지 않은 노드 중 제일 비용이 적은 노드 선택
            Node curNode = priorityQueue.poll();
            int curv = curNode.vertex;
            int curw = curNode.weight;
            int curStatus = curNode.trapStatus;

            // 현재 노드가 도착 노드라면 정답 갱신. 도착 노드 이후로 더 볼 필요 X. 따라서 continue
            if(curv == end) {
                answer = Math.min(answer,curw);
                continue;
            }

            // 처리해야할 노드의 경로가 이미 저장된 경로보다 길면 continue
            if(curw > dist[curv][curStatus])
                continue;

            // 현재 노드가 트랩이면 현재 상태 변화 (현재 트랩 켜주기)
            if(trapMap.containsKey(curv))
                curStatus ^= (1<<trapMap.get(curv));

            // 인접 노드 탐색
            for(Edge nextNode : graph[curv]) {
                int nextv = nextNode.vertex;
                int nextw = nextNode.weight;
                boolean isReverseEdge = nextNode.isReverse;
                // curv -> nextv가 역방형인 상태이고, 뒤집어진 간선일 떄 다음 노드 갈 수 있음
                // curv -> nextv가 정방형인 상태이고, 뒤집어지지 않은 간선일 때 다음 노드 갈 수 있음
                boolean isRight = checkIsRight(curv,nextv,curStatus);
                if(isRight && isReverseEdge)
                    continue;
                else if(!isRight && !isReverseEdge)
                    continue;
                if(dist[nextv][curStatus] > nextw + curw) {
                    dist[nextv][curStatus] = nextw + curw;
                    priorityQueue.add(new Node(nextv, nextw + curw, curStatus));
                }
            }

        }
        return answer;
    }
    // curv 에서 nextv가 정방형이면  true, 역방형이면 false
    boolean checkIsRight(int curv, int nextv, int curStatus) {
        // 둘 다 트랩이 아닌 경우 정방형
        if(!trapMap.containsKey(curv) && !trapMap.containsKey(nextv))
            return true;
        // 둘 다 트랩인 경우
        if(trapMap.containsKey(curv) && trapMap.containsKey(nextv)) {
            int curvBit = 1 << trapMap.get(curv);
            int nextvBit = 1 << trapMap.get(nextv);
            boolean isCurvActive = (curStatus & curvBit) == curvBit;
            boolean isNextvActice = (curStatus & nextvBit) == nextvBit;
            // 하나만 활성 되어있는 경우 역방형 (두 값이 다른 경우)
            // 둘 다 활성되어있거나 둘 다 비활성인 경우 정방형 (두 값이 같은 경우)
            return isCurvActive == isNextvActice ? true : false;
        }
        // 하나만 트랩인 경우
        // 활성되어있는 경우 역방형
        // 활성되지 않은 경우 정방형
        if(trapMap.containsKey(curv)) {
            int curvBit = 1 << trapMap.get(curv);
            boolean isCurvActive = (curStatus & curvBit) == curvBit;
            return !isCurvActive;
        }
        else if(trapMap.containsKey(nextv)) {
            int nextvBit = 1 << trapMap.get(nextv);
            boolean isNextvActice = (curStatus & nextvBit) == nextvBit;
            return !isNextvActice;
        }
        return true;
    }
}
