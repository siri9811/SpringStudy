package com.example.firstproject.ioc;

import org.springframework.stereotype.Component;

@Component
public class IngredientFactory
{

    public Ingredient get(String menu) {
        switch (menu) {
            case "돈가스":
                return new Pork("연돈 ");
            case "스테이크":
                return new Beef("한우 꽃등심 ");
            case "후라이드 치킨":
                return new Chicken("야무진 ");
            default:
                return null;
        }
    }
}
