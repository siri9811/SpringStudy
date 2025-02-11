package com.example.firstproject.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {
    @Test
    public void 자바_객체를_JSON으로_변환() throws JsonProcessingException {
        // 준비
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("통새우 패티", "순쇠고기 패티", "토마토", "소스");
        Burger burger = new Burger("맥도날드 빅맥", 5500, ingredients);

        // 수행
        // writeValueAsString(자바객체)를 사용하면 문자열의 json을 반환해준다.
        String json = objectMapper.writeValueAsString(burger); // 예외 발생 -> throws 처리

        // 예상
        String expected = "{\"name\":\"맥도날드 빅맥\",\"price\":5500,\"ingredients\":[\"통새우 패티\",\"순쇠고기 패티\",\"토마토\",\"소스\"]}";

        // 검증
        assertEquals(expected, json);
        JsonNode jsonnode = objectMapper.readTree(json);
        System.out.println(jsonnode);
    }

    @Test
    public void JSON을_자바_객체로_변환() throws JsonProcessingException {
        //준비
        ObjectMapper objectMapper = new ObjectMapper();
        /*
    {
        "name" : "맥도날드 빅맥",
        "price" : 5500,
        "ingredients" : [ "통새우 패티", "순쇠고기 패티", "토마토", "소스" ]
    }
    */
        ObjectNode objectnode = objectMapper.createObjectNode();
        objectnode.put("name", "맥도날드 슈비버거");
        objectnode.put("price", 5500);

        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add("통새우 패티");
        arrayNode.add("순쇠고기 패티");
        arrayNode.add("토마토");
        arrayNode.add("스파이스 어니언 소스");
        objectnode.set("ingredients",arrayNode);
        String json = objectnode.toString();


        //수행
        Burger burger = objectMapper.readValue(json, Burger.class);

        //예상
        List<String> ingredients = Arrays.asList("통새우 패티", "순쇠고기 패티", "토마토", "스파이스 어니언 소스");
        Burger expected = new Burger("맥도날드 슈비버거", 5500, ingredients);


        //비교
        assertEquals(expected.toString(), burger.toString());
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
        System.out.println(burger.toString());
    }
}