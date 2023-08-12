package com.preonboarding.comment;

import com.preonboarding.board.Board;
import com.preonboarding.member.Member;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper {

    public Comment toEntity(CommentDto.Post dto, Board board, Member member) {
        return new Comment(dto.getContent(), board, member);
    }

    public CommentDto.Response toResponse(Comment comment) {
        return CommentDto.Response.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .nickname(comment.getMember().getNickname())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

    public List<CommentDto.Response> toListResponse(List<Comment> comments) {
        return comments.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}