package com.preonboarding.board;

import com.preonboarding.global.dto.MultiResponse;
import com.preonboarding.member.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardDto.Response> createBoard(@RequestBody BoardDto.Post dto,
                                                         @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        BoardDto.Response response = boardService.createBoard(dto, memberPrincipal);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<MultiResponse<BoardDto.Response>> getBoards(@PageableDefault Pageable pageable) {
        Page<BoardDto.Response> response = boardService.getBoards(pageable);
        return new ResponseEntity<>(new MultiResponse<>(response.getContent(), response), HttpStatus.OK);
    }

    @GetMapping("/{board-id}")
    public ResponseEntity<BoardDto.Response> getBoard(@PathVariable("board-id") Long boardId) {
        BoardDto.Response response = boardService.getBoard(boardId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{board-id}")
    public ResponseEntity<BoardDto.Response> modifyBoard(@PathVariable("board-id") Long boardId,
                                                         @RequestBody BoardDto.Patch dto,
                                                         @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        BoardDto.Response response = boardService.modifyBoard(boardId, dto, memberPrincipal);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{board-id}")
    public ResponseEntity<BoardDto.Response> deleteBoard(@PathVariable("board-id") Long boardId,
                                                         @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        boardService.deleteBoard(boardId, memberPrincipal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}