package com.preonboarding.board;

import com.preonboarding.comment.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardMapper {

    public Board toEntity(BoardDto.Post dto) {
        return Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    public BoardDto.Response toResponse(Board board) {
        return new BoardDto.Response(board);
    }

    public Page<BoardDto.Response> toPageResponse(Page<Board> boards) {
        return boards.map(this::toResponse);
    }

    public BoardDto.DetailResponse toDetailResponse(Board board, List<CommentDto.Response> comments) {
        return new BoardDto.DetailResponse(board, comments);
    }
}