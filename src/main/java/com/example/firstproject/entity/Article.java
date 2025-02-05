package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor //생성자
@NoArgsConstructor // 디폴트 생성자
@ToString
@Getter
public class Article {

    @Id // 대표값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 어노테이션
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;

    }

}
