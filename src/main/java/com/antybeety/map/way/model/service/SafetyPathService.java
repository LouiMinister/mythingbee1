package com.antybeety.map.way.model.service;

import com.antybeety.map.way.model.vo.GraphAStar;
import com.antybeety.map.way.model.vo.NodeData;

import java.util.*;

// 안전 길찾기 찾아주는 서비스
public class SafetyPathService {

    private final GraphAStar graph;

    public SafetyPathService(GraphAStar graphAStar) {
        this.graph = graphAStar;
    }

    // extend comparator.
    // 노드 데이터끼리 총 비용을 비교할 수 있도록 Comparator 재정의
    public class NodeComparator implements Comparator<NodeData> {
        public int compare(NodeData nodeFirst, NodeData nodeSecond) {
            if (nodeFirst.getF() > nodeSecond.getF()) return -1;    // F : 예측 안전 가중치 -> 높을 수록 안전
            if (nodeSecond.getF() > nodeFirst.getF()) return 1;
            return 0;
        }
    }

    /**
     * Implements the A-star algorithm and returns the path from source to destination
     *  A star 알고리즘을 구현하는 소스코드, 출발지부터 도착지까지의 경로를 반환한다.
     * @param source        the source nodeid   -> 출발지 노드 id
     * @param destination   the destination nodeid  -> 도착지 노드 id
     * @return              the path from source to destination -> 출발지부터 도착지까지의 경로 반환
     */
    public List<Long> astar(Long source, Long destination) {
        /**
         * http://stackoverflow.com/questions/20344041/why-does-priority-queue-has-default-initial-capacity-of-11
         */
        // 우선선위 큐(초기 용량, 비교수단 Comparator)
        final Queue<NodeData> openQueue = new PriorityQueue<NodeData>(11, new NodeComparator());

        NodeData sourceNodeData = graph.getNodeData(source);
        sourceNodeData.setG(0); // 출발지점이니까 0
        sourceNodeData.calcF(destination);  // 도착지까지의 총 비용 계산
        openQueue.add(sourceNodeData);  // 출발 노드 큐에 삽입

        // key: 노드, value : 부모 노드   -> 키에 해당하는 노드는 value에 해당하는 노드를 거쳐서 왔다는 뜻
        final Map<Long, Long> cameFrom = new HashMap<Long, Long>(); // 경로 Map
        final Set<NodeData> closedList = new HashSet<NodeData>(); // 닫힌 목록 -> 더 이상 볼 필요 없는 목록

        // 큐 : 열린 목록
        // 큐가 비기 전 까지 무한 반복 -> 큐가 빈거면 경로가 없다는 뜻
        while (!openQueue.isEmpty()) {
            final NodeData nodeData = openQueue.poll();  // 큐에서 하나 poll

            // 도착지 노드 발견하면 경로에 추가하고 종료
            if (nodeData.getNodeId().equals(destination)) {
                return path(cameFrom, destination);
            }

            // poll한 노드 닫힌 목록에 추가
            closedList.add(nodeData);

            // poll한 노드의 인접 노드를 하나씩 뽑아서 진행
            for (Map.Entry<NodeData, Double> neighborEntry : graph.edgesFrom(nodeData.getNodeId()).entrySet()) {
                // entrySet() : Returns a Set view of the mappings contained in this map.

                NodeData neighbor = neighborEntry.getKey();

                // 닫힌 목록에 있으면 볼 필요 없음
                if (closedList.contains(neighbor)) continue;

                double distanceBetweenTwoNodes = neighborEntry.getValue();  // 두 노드 사이의 비용
                double tentativeG = distanceBetweenTwoNodes + nodeData.getG();  // 두 노드 사이의 비용 + poll한 노드의 G값

                // poll한 노드 거쳐서 온 G값이 더 작으면 G값 변경
                if (tentativeG < neighbor.getG()) {
                    neighbor.setG(tentativeG);
                    neighbor.calcF(destination);

                    // 경로 map에 추가 -> 내가 어디서 왔는가 ( 자기 부모(?) 노드 입력 )
                    cameFrom.put(neighbor.getNodeId(), nodeData.getNodeId());
                    // 큐에 이웃이 포함 안되어 있으면 추가
                    if (!openQueue.contains(neighbor)) {
                        openQueue.add(neighbor);
                    }
                }
            }
        }
        return null;
    }

    // 경로 반환하는 메서드
    private List<Long> path(Map<Long, Long> cameFrom, Long destination) {
        // assert boolean 식;    -> 개발자가 true이라고 생각하는 식을 적는다. boolean 식이 성공한 경우에만 프로그램이 수행
        assert cameFrom != null;
        assert destination != null;

        final List<Long> pathList = new ArrayList<>();
        pathList.add(destination);
        while (cameFrom.containsKey(destination)) {
            destination = cameFrom.get(destination);
            pathList.add(destination);
        }
        Collections.reverse(pathList);
        return pathList;
    }
}
