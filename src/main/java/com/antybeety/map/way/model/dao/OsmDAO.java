package com.antybeety.map.way.model.dao;


import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@Repository
public class OsmDAO {

    /**
     * "osm/OsmFile_lines.json" 파일을 읽어서 노드, 엣지 정보를 반환
     *  json 에 line에 대한 정보가 연속된 자표로 표현되어 있다.
     *
     * @return 결과를 반환할 컬랙션. Queue<Queue<Double[2]>> 타입
     *         Double[2] 에는 위도 경도 정보, 안쪽 Queue 에는 한 라인에 해당하는 연속 위도 경도 정보가 저장됨
     */
    public Queue<ConcurrentLinkedQueue<Double[]>> getOsmLines() {

        // json 파일을 읽기 위한 Mapper
        ObjectMapper om = new ObjectMapper();

        // 결과를 반환할 컬랙션. Queue<Queue<Double[2]>> 타입
        // Double[2] 에는 위도 경도 정보, 안쪽 Queue 에는 한 라인에 해당하는 연속 위도 경도 정보가 저장됨
        ConcurrentLinkedQueue<ConcurrentLinkedQueue<Double[]>> linesQueue= new ConcurrentLinkedQueue<>();
        try{
            //Tree로 json 파일을 읽어들임.
            JsonNode rootJson = om.readTree(new ClassPathResource("osm/OsmFile_lines.json").getFile());

            //features 키의 Value가 배열 형식 형식으로 저장되어 있어서 Iterator로 반환받음.
            Iterator<JsonNode> linesJson = rootJson.path("features").getElements();

            //Looping
            while(linesJson.hasNext()){
                //n번째 루프(features 키 값에 대한 n번째 value) 에서의 데이터
                JsonNode lineJson = linesJson.next();

                //feature 키값에 대한 n번째 value도 배열 형식(좌표의 배열 데이터)으로 되어 있어 Iterator로 반환
                Iterator<JsonNode> pointsJson =lineJson.path("geometry").path("coordinates").getElements();

                //Queue에 Loop를 돌며 좌표들을 Queue에 저장
                ConcurrentLinkedQueue<Double[]> points = new ConcurrentLinkedQueue<>();
                while(pointsJson.hasNext()){
                    JsonNode pointJson = pointsJson.next();
                    Double[] tempPointSet= new Double[2];
                    tempPointSet[0] = pointJson.path(0).asDouble();
                    tempPointSet[1] = pointJson.path(1).asDouble();

                    //Queue 에 좌표들을 저장해 라인 데이터를 저장
                    points.add(tempPointSet);
                }
                //라인 데이터들을 Queue에 저장
                linesQueue.add(points);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return linesQueue;
    }
/*    @PostConstruct
    public void afterPropertiesSet() throws Exception {

        Queue<ConcurrentLinkedQueue<Double[]>> temp = getOsmJson();

        for (ConcurrentLinkedQueue<Double[]> c : temp){
            for(Double[] s: c){
                System.out.println(Arrays.toString(s));
            }
            System.out.println("------------------------------");
        }
    }*/
}
