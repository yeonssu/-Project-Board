package com.preonboarding.board;

import org.springframework.stereotype.Component;

@Component
public class BoardMapper {

    public Board toEntity(BoardDto.Post dto) {
        return Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    public BoardDto.Response toResponse(Board board) {
        return BoardDto.Response.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .nickname(board.getMember().getNickname())
                .viewCount(board.getViewCount())
                .likeCount(board.getLikeCount())
                .build();
    }
}
