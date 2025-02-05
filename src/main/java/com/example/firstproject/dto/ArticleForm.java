package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@ToString
public class ArticleForm {

    //private Long id; id가 필요한지 고민
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(title, content);
    }
}
