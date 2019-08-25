package com.antybeety.map.way.model.service;

import com.antybeety.map.model.dao.FacilityMarkDAOImpl;
import com.antybeety.map.model.service.FacilityDisplayService;
import com.antybeety.map.model.vo.FacilityMarkVO;
import com.antybeety.map.way.model.dao.EdgeDAO;
import com.antybeety.map.way.model.dao.NodeDAO;
import com.antybeety.map.way.model.vo.GraphAStar;
import com.antybeety.map.way.model.vo.NodeData;
import com.antybeety.map.way.model.vo.NodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

// 안전 길찾기 찾아주는 서비스
@Service
public class SafetyPathService {

    @Autowired
    private FacilityDisplayService fDisplay;
    @Autowired
    private NodeDAO nodeDao;
    @Autowired
    private EdgeDAO edgeDAO;
    @Autowired
    private DistanceCalcService distanceCalcService;

    private GraphAStar graph;
    private final double CIRCLE_RATIO = Math.sqrt(2);       //원에 내적하는 사각형과 원의 반지름 과의 배율
    private final double MATCH_INIT_RADIUS= 0.00003000000;  //matchNode() 에서 검색하는 최초 반경

    public SafetyPathService(){}

    public SafetyPathService(double startLat, double startLon, double endLat, double endLon){

        Map<Long, NodeData> nodeIdNodeData = new HashMap<>();
        Map<Long, Map<Long,Double>> heuristicMap = new HashMap<>();
        Map<Long, Map<NodeData, Double>> graph = new HashMap<>();

        // 모든 노드말고 영역을 받아서 영역 내의 노드 들만 가져와야함
        List<NodeVO> nodeList = new NodeDAO().getAllNode();
        // 모든 노드 데이터 맵 초기화
        for(NodeVO node : nodeList){
            nodeIdNodeData.put(node.getId(),new NodeData(node.getId(),node.getLat(),node.getLng()));
        }


}

    public SafetyPathService(GraphAStar graphAStar) {
        this.graph = graphAStar;
    }

    // extend comparator.
    // 노드 데이터끼리 총 비용을 비교할 수 있도록 Comparator 재정의
    public class NodeComparator implements Comparator<NodeData> {
        public int compare(NodeData nodeFirst, NodeData nodeSecond) {
            if (nodeFirst.getF() < nodeSecond.getF()) return -1;    // F : 거리 + 안전수치
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
    public List<NodeVO> astar(Long source, Long destination) {
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
               List<Long> temp = path(cameFrom, destination);
               List<NodeVO> result = new ArrayList<>();
               for(long id : temp){
                   result.add(nodeDao.getNode(id));
               }
               return result;
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

    //파라미터로 받은 위경도와 가장 가까운 노드를 찾는 메서드
    public NodeVO matchNode(double lat, double lng){

        DistanceCalcService calcService = new DistanceCalcService();

        int ratio =1;   //배율
        double radiusMultiplyRatio = ratio*MATCH_INIT_RADIUS;   //배율과 반지름의 곱

        List<NodeVO> capturedNode = new ArrayList<>();      //찾은 노드

        while(true) {   //노드 하나라도 찾을때 까지 반복
            //사각형 안에서 찾기
            capturedNode.addAll(nodeDao.searchNodesByArea(lat - radiusMultiplyRatio, lng - radiusMultiplyRatio,
                    lng + radiusMultiplyRatio, lng + radiusMultiplyRatio));
            //찾은 값이 있을 경우 길이에 CIRCLE_RATION를 곱한 사각형안에서 찾기
            if (capturedNode.size() > 0) {
                capturedNode.addAll(nodeDao.searchNodesByArea(lat - radiusMultiplyRatio * CIRCLE_RATIO, lng - radiusMultiplyRatio * CIRCLE_RATIO,
                        lat + radiusMultiplyRatio*CIRCLE_RATIO, lng + radiusMultiplyRatio * CIRCLE_RATIO));
                break;
            }

            ratio++; //배율증가
            radiusMultiplyRatio= ratio*MATCH_INIT_RADIUS;
        }

        //찾은 노드들 중 가장 가까운 노드 찾기
        int index =0;
        NodeVO resNode = null;
        int resIndex;
        double resDistance = Double.POSITIVE_INFINITY;

        double distanceTmp;
        for(NodeVO vo : capturedNode){  //선택된 노드들중 최소 거리인 노드 찾기
            distanceTmp=calcService.calcDistance(lat,lng,vo.getLat(),vo.getLng());
            if(resDistance>distanceTmp){    //더 작은 노드일 경우
                resDistance = distanceTmp;
                resIndex=index;

            }
            index++;
        }
        index--;
        return capturedNode.get(index);
    }
    public double calcSafetyValue(Map<String, Object> bounds) {
        // 영역 내의 모든 시설물 가져오기
        List<FacilityMarkVO> allFacility = fDisplay.searchAroundFacilities(bounds);

        int safetyValueSum = 0;
        // 영역 내의 총 안전수치 점수
        for(FacilityMarkVO v : allFacility){
            switch (v.getCode().substring(0,2)){
                case "CC":
                    safetyValueSum += 8;
                    break;
                case "LI":
                    safetyValueSum += 3;
                    break;
                case "PD":
                    safetyValueSum += 50;
                    break;
                case "CS":
                    safetyValueSum += 20;
                    break;
                case "BE":
                    safetyValueSum ++;
                    break;
            }
        }
        return distanceCalcService.calcDistance(bounds) - safetyValueSum / distanceCalcService.calcArea(bounds);
    }


}