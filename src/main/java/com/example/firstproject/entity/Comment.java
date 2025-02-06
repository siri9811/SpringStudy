package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public Comment(Article article, String nickname, String body) {
        this.article = article; // 객체 자체를 저장
        this.nickname = nickname;
        this.body = body;
    }

    public static Comment createComment(CommentDto dto, Article article) {
        //예외 처리
        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");

        //엔티티 생성 및 반환
        return new Comment(
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(Comment comment) {
        //예외 발생
        if(this.id != comment.getId())
            throw new IllegalArgumentException("잘못된 id입니다");

        //객체를 갱신
        if(comment.getNickname() != null)
            this.nickname = comment.getNickname();

        if(comment.getBody() != null)
            this.body = comment.getBody();
    }
}
