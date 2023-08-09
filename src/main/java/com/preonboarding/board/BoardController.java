package com.preonboarding.board;

import com.preonboarding.member.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
