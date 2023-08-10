package com.preonboarding.comment;

import com.preonboarding.member.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards/{board-id}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto.Response> createComment(@PathVariable("board-id") Long boardId,
                                                             @AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                                             @RequestBody CommentDto.Post dto) {
        CommentDto.Response response = commentService.createComment(boardId, memberPrincipal, dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}