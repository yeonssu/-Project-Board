package com.preonboarding.comment;

import com.preonboarding.member.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto.Response> create(@AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                                      @RequestBody @Valid CommentDto.Post dto) {
        CommentDto.Response response = commentService.create(memberPrincipal, dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentDto.Response> update(@PathVariable Long id,
                                                      @AuthenticationPrincipal MemberPrincipal memberPrincipal,
                                                      @RequestBody @Valid CommentDto.Patch dto) {
        CommentDto.Response response = commentService.update(id, memberPrincipal, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentDto.Response> delete(@PathVariable Long id,
                                                      @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        commentService.delete(id, memberPrincipal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}