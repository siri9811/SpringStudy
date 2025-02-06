package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {

    @JsonProperty("article_id")
    private Long articleId;
    private String nickname;
    private String body;



    public static CommentDto creatCommentDto(Comment comment) {

        return new CommentDto(
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );

    }
}
