package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comments;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 조회: 댓글 목록
        List<Comment> comments  = commentRepository.findByArticleId(articleId);

        // 변환: 엔티티 -> dto
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for (int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.creatCommentDto(c);
//            dtos.add(dto);
//        }
        // 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.creatCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외 처리
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다"));// 댓글 엔티티 생성

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);


        // 댓글 엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);

        // DTO로 변경하여 반환
        return CommentDto.creatCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, Comment comment) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("대상 댓글이 없습니다."));
        //댓글 수정
        target.patch(comment);
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티를 dto로 변환 및 반환
        return CommentDto.creatCommentDto(updated);
    }

    public CommentDto delete(Long id) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("삭제할 존재하지 않습니다"));

        //댓글 삭제
         commentRepository.delete(target);

         //삭제 댓글을 DTO로 반환
        return CommentDto.creatCommentDto(target);
    }
}
