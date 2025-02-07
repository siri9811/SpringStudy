package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comments;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {


    private final CommentRepository commentRepository;


    private final ArticleRepository articleRepository;
    // 댓글이 달린 게시글도 가져와야하기 때문에 ArticleRepository도 함께 가져오기

    public List<CommentDto> comments(Long articleId) {

        // 조회: 댓글 목록 조회(게시글 아이디를 통해 해당 게시글의 댓글 목록 조회)
//        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 변환: 엔티티 -> DTO
        // CommentApiController에서 List<Comment> -> List<CommentDto>로 반환하기로 했기때문에 엔티티 -> DTO로 변환
//        List<CommentDto> dtos = new ArrayList<CommentDto>();

        // 비어있는 dtos에다가 댓글들을 변환해서 add하기
//        for(int i = 0; i < comments.size(); i++) {
//            // comments 값을 하나하나 꺼내서 넣기
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c); // Dto로 변환
//            dtos.add(dto);
//        }

        // 반환
        return commentRepository.findByArticleId(articleId) // commentRepository에 목록조회
                .stream() // stream으로 변경
                .map(comment -> CommentDto.createCommentDto(comment)) //createCommentDto를 통해 comment를 하나하나전달하여 DTO로 변환
                .collect(Collectors.toList()); // map이 반환하는 값이 stream<Object>이기 때문에
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외 발생
        // .orElseThrow(() -> new IllegalArgumentException()) article이 없다면 예외발생시켜서 다음 코드가 실행되지 않는다.
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!! 대상 게시글이 없습니다."));

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        // 댓글 엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);

        // DTO로 변환하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {

        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));

        // 댓글 수정
        target.patch(dto);

        // 댓글 DB로 갱신
        Comment updated = commentRepository.save(target);

        // 댓글 엔티티를 DTO로 변환 및 반환
        return  CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {

        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상 댓글이 없습니다."));

        // 댓글 DB에서 삭제
        commentRepository.delete(target);

        // 삭제 댓글을 DTO로 반환
        return CommentDto.createCommentDto(target);
    }
}