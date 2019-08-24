package com.antybeety.map.way.model.vo;

import org.springframework.context.annotation.Lazy;

import java.util.*;

public class GraphAStar implements Iterable{
    /*
     * A map from the nodeId to outgoing edge.
     * An outgoing edge is represented as a tuple of NodeData and the edge length
     */
    //NodeId,Map<노드정보,거리길이>
    private final Map<Long, Map<NodeData, Double>> graph;
    /*
     * A map of heuristic from a node to each other node in the graph.
     */
    // 그래프에서 노드간의 추정비용 Map
    private final Map<Long, Map<Long, Double>> heuristicMap;
    /*
     * A map between nodeId and nodedata.
     */
    // 노드 id와 노드 data사이의 Map
    private final Map<Long, NodeData> nodeIdNodeData;

/*    public GraphAStar(Map<Long, Map<Long, Double>> heuristicMap) {
        if (heuristicMap == null) throw new NullPointerException("The huerisic map should not be null");
        graph = new HashMap<Long, Map<NodeData, Double>>();
        nodeIdNodeData = new HashMap<Long, NodeData>();
        this.heuristicMap = heuristicMap;
    }*/

    private GraphAStar (){
        graph =null;
        nodeIdNodeData = null;
        heuristicMap =null;
    }

    public static GraphAStar getInstance(){
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final GraphAStar INSTANCE = new GraphAStar();
    }

    /**
     * Adds a new node to the graph.
     * Internally it creates the nodeData and populates the heuristic map concerning input node into node data.
     *
     * @param nodeId the node to be added
     */
    // 그래프에 새로운 노드 추가. 노드에 대한 heuristic map을 노드 데이터에 채운다
    public void addNode(Long nodeId) {
        if (nodeId == null) throw new NullPointerException("The node cannot be null");
        if (!heuristicMap.containsKey(nodeId)) throw new NoSuchElementException("This node is not a part of hueristic map");

        graph.put(nodeId, new HashMap<NodeData, Double>());
        nodeIdNodeData.put(nodeId, new NodeData(nodeId, heuristicMap.get(nodeId)));
    }

    /**
     * Adds an edge from source node to destination node.
     * There can only be a single edge from source to node.
     * Adding additional edge would overwrite the value
     *
     * @param nodeIdFirst   the first node to be in the edge    -> 연결할 노드1
     * @param nodeIdSecond  the second node to be second node in the edge   -> 연결할 노드2
     * @param weight        the weight of the edge. -> 노드1 부터 노드2 길이(가중치) -> 우리는 길이 + 가중치가 필요함
     */
    // 출발 노드에서 목적 노드까지 간선 추가
    // 출발 노드로부터 하나의 간선만 있을 수 있다.
    // 간선을 추가하면 값을 덮어쓸 수 있다
    public void addEdge(Long nodeIdFirst, Long nodeIdSecond, double weight) {
        if (nodeIdFirst == null || nodeIdSecond == null) throw new NullPointerException("The first nor second node can be null.");

        // 추정비용 맵에 해당 노드들이 없을 때
        if (!heuristicMap.containsKey(nodeIdFirst) || !heuristicMap.containsKey(nodeIdSecond)) {
            throw new NoSuchElementException("Source and Destination both should be part of the part of hueristic map");
        }
        // 그래프 맵에 해당 노드들이 없을 때
        if (!graph.containsKey(nodeIdFirst) || !graph.containsKey(nodeIdSecond)) {
            throw new NoSuchElementException("Source and Destination both should be part of the part of graph");
        }

        // 노드아이디로 노드 찾은다음에 그 노드에 상대방 노드까지의 경로 등록
        graph.get(nodeIdFirst).put(nodeIdNodeData.get(nodeIdSecond), weight);
        graph.get(nodeIdSecond).put(nodeIdNodeData.get(nodeIdFirst), weight);
    }

    /**
     * Returns immutable view of the edges
     *  간선에 대한 view
     * @param nodeId    the nodeId whose outgoing edge needs to be returned -> 검색할 nodeId
     * @return          An immutable view of edges leaving that node    -> 해당 노드에서 나가는 간선에 대한 view
     */
    public Map<NodeData, Double> edgesFrom (Long nodeId) {
        if (nodeId == null) throw new NullPointerException("The input node should not be null.");
        if (!heuristicMap.containsKey(nodeId)) throw new NoSuchElementException("This node is not a part of hueristic map");
        if (!graph.containsKey(nodeId)) throw new NoSuchElementException("The node should not be null.");

        return Collections.unmodifiableMap(graph.get(nodeId));
    }

    /**
     * The nodedata corresponding to the current nodeId.
     *  현재 노드에 해당하는 노드 데이터
     * @param nodeId    the nodeId to be returned   -> 검색할 노드
     * @return          the nodeData from the
     */
    public NodeData getNodeData (Long nodeId) {
        if (nodeId == null) { throw new NullPointerException("The nodeid should not be empty"); }
        if (!nodeIdNodeData.containsKey(nodeId))  { throw new NoSuchElementException("The nodeId does not exist"); }
        return nodeIdNodeData.get(nodeId);
    }

    /**
     * Returns an iterator that can traverse the nodes of the graph
     *  그래프의 노드를 이동할 수 있는 iterater 반환
     * @return an Iterator.
     */
    @Override public Iterator iterator() {
        return graph.keySet().iterator();
    }
}
