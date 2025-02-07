package com.example.firstproject.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChefTest {

    @Autowired
    IngredientFactory factory;

    @Autowired
    Chef chef;

    @Test
    void 돈가스_요리하기() {
        //준비
        //IngredientFactory factory = new IngredientFactory();
        //Chef chef = new Chef(factory);
        String menu = "돈가스";
        //수행
        String food = chef.cook(menu);
        //예상
        String expected = "연돈 돈가스";
        //검증
        assertEquals(expected, food);
        System.out.println(food);
    }


    @Test
    void 스테이크_요리하기() {
        //준비


        //IngredientFactory factory = new IngredientFactory();
        //Chef chef = new Chef(factory);
        String menu = "스테이크";
        //수행
        String food = chef.cook(menu);
        //예상
        String expected = "한우 꽃등심 스테이크";
        //검증
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 후라이드_치킨_요리하기() {
        //준비
        //IngredientFactory factory = new IngredientFactory();
        //Chef chef = new Chef(factory);
        String menu = "후라이드 치킨";
        //수행
        String food = chef.cook(menu);
        //예상
        String expected = "야무진 후라이드 치킨";
        //검증
        assertEquals(expected, food);
        System.out.println(food);
    }
}