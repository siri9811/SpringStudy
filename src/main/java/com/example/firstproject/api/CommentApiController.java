package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;

    @GetMapping("/api/articles/{id}/comment")
    public ResponseEntity<List<CommentDto>> getComment(@PathVariable Long id) {
        //서비스에게 위임
        List<CommentDto> dtos = commentService.comments(id);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    //댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto) {
        // 서비스에게 위임
        CommentDto createDto = commentService.create(articleId,dto);

        // 결과응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }

    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody Comment comment) {
        //서비스에게 위임
        CommentDto updatedDto = commentService.update(id, comment);

        //결과응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id) {
        //서비스에게 위임
        CommentDto deleteDto = commentService.delete(id);

        //결과응답
        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }





}
