package com.example.firstproject.ioc;

import org.springframework.stereotype.Component;

@Component
public class Chef {

        private IngredientFactory factory;

        //셰프가 식재료 공장과 협업하기 위한 DI
        public Chef(IngredientFactory factory) {
            this.factory = factory;
        }

        public String cook(String menu) {
            //재료준비
            Ingredient ingredient = factory.get(menu);
            //요리반환
            return ingredient.getName() + menu;
        }
    }

