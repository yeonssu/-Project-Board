package com.preonboarding.board;

import com.preonboarding.global.dto.MultiResponse;
import com.preonboarding.member.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardDto.Response> create(@RequestBody @Valid BoardDto.Request dto,
                                                    @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        BoardDto.Response response = boardService.create(dto, memberPrincipal);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<MultiResponse<BoardDto.Response>> getAll(@RequestParam int page) {
        Page<BoardDto.Response> response = boardService.getAll(PageRequest.of(page - 1, 20));
        MultiResponse<BoardDto.Response> pageResponse = new MultiResponse<>(response.getContent(), response);
        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto.DetailResponse> get(@PathVariable Long id) {
        BoardDto.DetailResponse response = boardService.get(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BoardDto.Response> update(@PathVariable Long id,
                                                    @RequestBody @Valid BoardDto.Request dto,
                                                    @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        BoardDto.Response response = boardService.update(id, dto, memberPrincipal);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BoardDto.Response> delete(@PathVariable Long id,
                                                    @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        boardService.delete(id, memberPrincipal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/likes")
    public ResponseEntity<BoardDto.Response> like(@PathVariable Long id) {
        boardService.like(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}