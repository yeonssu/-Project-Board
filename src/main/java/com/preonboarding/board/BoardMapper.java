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
        return BoardDto.Response.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .viewCount(board.getViewCount())
                .likeCount(board.getLikeCount())
                .nickname(board.getMember().getNickname())
                .build();
    }

    public BoardDto.DetailResponse toDetailResponse(Board board, List<CommentDto.Response> comments) {
        return BoardDto.DetailResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .viewCount(board.getViewCount() + 1)
                .likeCount(board.getLikeCount())
                .nickname(board.getMember().getNickname())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .comments(comments)
                .build();
    }

    public Page<BoardDto.Response> toPageResponse(Page<Board> boards) {
        return boards.map(this::toResponse);
    }
}