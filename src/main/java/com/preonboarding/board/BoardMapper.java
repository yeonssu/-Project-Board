package com.preonboarding.board;

import com.preonboarding.comment.CommentDto;
import com.preonboarding.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoardMapper {

    public Board toEntity(BoardDto.Request dto, Member member) {
        return new Board(dto.getTitle(), dto.getContent(), member);
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