package com.preonboarding.board;

import com.preonboarding.comment.CommentDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public class BoardDto {

    @Getter
    public static class Request {

        @NotBlank
        private String title;

        @NotBlank
        private String content;
    }

    @Getter
    @SuperBuilder
    public static class Response {

        private final Long id;

        private final String title;

        private final String content;

        private final Long viewCount;

        private final Long likeCount;

        private final String nickname;

        public Response(Long id, String title, String content, Long viewCount, Long likeCount, String nickname) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.viewCount = viewCount;
            this.likeCount = likeCount;
            this.nickname = nickname;
        }
    }

    @Getter
    @SuperBuilder
    public static class DetailResponse extends Response {

        private final LocalDateTime createdAt;

        private final LocalDateTime updatedAt;

        private final List<CommentDto.Response> comments;

        public DetailResponse(Long id, String title, String content, Long viewCount, Long likeCount, String nickname,
                              LocalDateTime createdAt, LocalDateTime updatedAt, List<CommentDto.Response> comments) {
            super(id, title, content, viewCount, likeCount, nickname);
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.comments = comments;
        }
    }
}
