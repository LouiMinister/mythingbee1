package com.antybeety.map.way.model.service;

import com.antybeety.map.model.dao.FacilityMarkDAOImpl;
import com.antybeety.map.model.service.FacilityDisplayService;
import com.antybeety.map.model.vo.FacilityMarkVO;
import com.antybeety.map.way.model.dao.EdgeDAO;
import com.antybeety.map.way.model.dao.NodeDAO;
import com.antybeety.map.way.model.vo.EdgeVO;
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
    private EdgeDAO edgeDao;
    @Autowired
    private DistanceCalcService distanceCalcService;

    private GraphAStar graph;
    private final double CIRCLE_RATIO = Math.sqrt(2);       //원에 내적하는 사각형과 원의 반지름 과의 배율
    private final double MATCH_INIT_RADIUS= 0.00003000000;  //matchNode() 에서 검색하는 최초 반경
    private final double CAPTURE_PADDING=0.00015000000;

    private List<FacilityMarkVO> facilities;

    public SafetyPathService(){}

    public SafetyPathService(double startLat, double startLon, double endLat, double endLon){
        initAstarGraph(startLat,startLon,endLat,endLon);
    }

    public void initService(double startLat, double startLon, double endLat, double endLon){
        initAstarGraph(startLat, startLon, endLat, endLon);
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
                    lat + radiusMultiplyRatio, lng + radiusMultiplyRatio));
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

    public double calcSafetyValue(double lat1, double lng1, double lat2, double lng2) {
        // 영역 내의 모든 시설물 가져오기

        List<FacilityMarkVO> allFacility = facilities;
        List<FacilityMarkVO> facilitiesInRectangle = new ArrayList<FacilityMarkVO>();

        if(!(lat1<lat2)){       //왼쪽아래점, 오른쪽위점으로 세팅
            double temp = lat1;
            lat1= lat2;
            lat2= temp;
        }
        if(!(lng1<lng2)){
            double temp = lng1;
            lng1= lng2;
            lng2=temp;
        }

        double fLat=0;
        double fLng=0;
        for(FacilityMarkVO facil :allFacility){     //allFacility중 영역 안 퍼실리티만 배열에 추가
            fLat=facil.getLat();
            fLng=facil.getLng();
            if(fLat>lat1 && fLat<lat2 && fLng> lng1 && fLng < lng2){
                facilitiesInRectangle.add(facil);
            }
        }


        Map<String, Object> bounds = new HashMap<>();   //bound 위경도 변경
        bounds.put("la",lat1);
        bounds.put("ea",lng1);
        bounds.put("ka",lat2);
        bounds.put("ja",lng2);

        int safetyValueSum = 0;
        // 영역 내의 총 안전수치 점수
        for(FacilityMarkVO v : facilitiesInRectangle){
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

    public List<FacilityMarkVO> searchAllFacility(double lat1, double lng1, double lat2, double lng2){
        Map<String,Object> bounds = new HashMap<String, Object>();
        bounds.put("la",lat1);
        bounds.put("ea",lng1);
        bounds.put("ka",lat2);
        bounds.put("ja",lng2);

        List<FacilityMarkVO> facilitiesRes = fDisplay.searchAroundFacilities(bounds);

        return facilitiesRes;
    }


    public void initAstarGraph(double lat1, double lng1, double lat2, double lng2){
        Map<String,List<?>> nodeEdge = searchNodeEdgeForGraph(lat1, lng1, lat2, lng2);

        List<NodeVO> nodes = (List<NodeVO>) nodeEdge.get("nodes");      //영역 안 모든 노드정보
        List<EdgeVO> edges = (List<EdgeVO>) nodeEdge.get("edges");      //영역 안 모든 엣지정보

        facilities = searchAllFacility(lat1, lng1, lat2, lng2);    //영역 안 모든 시설물 정보

        Map<Long ,Map<Long,Double>> heuristicMap = new HashMap<Long, Map<Long, Double>>(); //휴리스틱값

        long node_sId=0;
        long node_eId=0;
        double hScore=0;
        int nodeSize= nodes.size();

        if(nodeSize<2){       //노드 개수가 두개보다 작을경우 휴리스틱 값이 없다

        }                   //노드 개수가 두개 이상일 경우
        else {
            for (int i = 0; i < nodeSize; i++) {
                HashMap<Long,Double> tempMap = new HashMap<>();
                for (int j = 0; j < nodeSize; j++) {
                    if(i==j){
                        tempMap.put(nodes.get(j).getId(),0.0);
                        continue;
                    }     // 시작 노드와 끝 노드가 같은 경우에 휴리스틱은 없다
                    hScore=calcSafetyValue(nodes.get(i).getLat(), nodes.get(i).getLng(), nodes.get(j).getLat(), nodes.get(j).getLng());
                    tempMap.put(nodes.get(j).getId(),hScore);
                }
                heuristicMap.put(nodes.get(i).getId(), tempMap);
            }
        }

        graph= new GraphAStar(nodes, edges, heuristicMap);
    }

    // 영역 안의 노드와 엣지들을 반환하는 메서드
    public Map<String,List<?>> searchNodeEdgeByArea(double lat1, double lng1, double lat2, double lng2){

       List<NodeVO> capturedNodes= nodeDao.searchNodesByArea(lat1, lng1, lat2, lng2);
       List<EdgeVO> capturedEdges= edgeDao.searchEdgesByArea(lat1, lng1, lat2, lng2);

       boolean startFlag = false;
       boolean endFlag = false;

       for(int i=0; i<capturedEdges.size(); i++){
           for(NodeVO n : capturedNodes){
               if(n.getId() == capturedEdges.get(i).getNodeStart()){
                   startFlag = true;
               }
               if(n.getId() == capturedEdges.get(i).getNodeEnd()){
                   endFlag = true;
               }
           }
           if(startFlag == false || endFlag == false){
               capturedEdges.remove(i);
           }
           startFlag = endFlag = false;
       }
       Map<String, List<? extends Object>> res= new HashMap<>();

       res.put("nodes",capturedNodes);
       res.put("edges", capturedEdges);

       return res;
    }

    public Map<String,List<?>> searchNodeEdgeForGraph(double lat1, double lng1, double lat2, double lng2){

        if((lat1>lat2)){
            double temp = lat1;
            lat1= lat2;
            lat2= temp;
        }
        if((lng1>lng2)){
            double temp = lng1;
            lng1= lng2;
            lng2=temp;
        }
        Map<String, List<?>> res= searchNodeEdgeByArea(lat1-CAPTURE_PADDING, lng1-CAPTURE_PADDING,
                lat2+CAPTURE_PADDING, lng2+CAPTURE_PADDING);

        return res;
    }


}