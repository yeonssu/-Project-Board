package com.preonboarding.comment;

import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity(CommentDto.Post dto) {
        return Comment.builder()
                .content(dto.getContent())
                .build();
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
}