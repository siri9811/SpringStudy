package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링 부트와 연동되어 테스팅됨
class ArticleServiceTest {


    @Autowired ArticleService articleService;

    @Test
    void index() {
        //예상
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        //실제
        List<Article> articles = articleService.index();

        //비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공() {
        //예상
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        //실제
        Article a = articleService.show(id);
        //비교
        assertEquals(expected.toString(), a.toString());
    }

    @Test
    void show_실패__존재하지_않는_id_입력() {
        //예상
        Long id = -1L;
        Article expected = null;
        //실제
        Article a = articleService.show(id);
        //비교
        assertEquals(expected, a);
    }





}