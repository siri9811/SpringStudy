package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA와 연동한 테스트!
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;
    @Autowired ArticleRepository articleRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* case 1: 4번 게시글의 모든 댓글 조회 */
        //입력 데이터 준비
        {
            Long articleId = 4L;
            //실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //예상하기

            Article article = articleRepository.findById(articleId)
                    .orElseThrow(() -> new RuntimeException("Article not found"));
            Comment a = new Comment(1L, article, "ㅇㅇ", "야미네");
            Comment b = new Comment(2L, article, "ㅇㅇ", "야미네");
            Comment c = new Comment(3L, article, "ㅇㅇ", "야미네");
            List<Comment> expected = Arrays.asList(a, b, c);
            //검증
            assertEquals(expected.toString(), comments.toString());
        }

        /* case 1: 2번 게시글의 모든 댓글 조회 */
        //입력 데이터 준비
        {
            Long articleId = 6L;
            //실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //예상하기

            Article article = articleRepository.findById(articleId)
                    .orElseThrow(() -> new RuntimeException("Article not found"));
            Comment a = new Comment(4L, article, "ㅇㅇ", "야미네");
            Comment b = new Comment(5L, article, "ㅇㅇ", "야미네");
            List<Comment> expected = Arrays.asList(a, b);
            //검증
            assertEquals(expected.toString(), comments.toString());
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        // 입력 데이터 준비
        String nickname = "ㅇㅇ";

        // 실제 수행: 특정 닉네임으로 댓글 조회
        List<Comment> comments = commentRepository.findByNickname(nickname);

        // 댓글이 null이 아닌지 확인
        assertNotNull(comments, "댓글 목록이 null이어서는 안됩니다.");

        // 댓글이 하나라도 있으면 성공
        assertTrue(!comments.isEmpty(), "댓글이 하나도 존재하지 않습니다.");
    }




}