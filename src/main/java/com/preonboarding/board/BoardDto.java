package com.preonboarding.board;

import com.preonboarding.comment.CommentDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class BoardDto {

    @Getter
    public static class Post {

        private String title;

        private String content;
    }

    @Getter
    public static class Patch extends Post {
    }

    @Getter
    public static class Response {

        private final Long id;

        private final String title;

        private final String content;

        private final Long viewCount;

        private final Long likeCount;

        private final String nickname;

        public Response(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.viewCount = board.getViewCount();
            this.likeCount = board.getLikeCount();
            this.nickname = board.getMember().getNickname();
        }
    }

    @Getter
    public static class DetailResponse extends Response {

        private final LocalDateTime createdAt;

        private final LocalDateTime updatedAt;

        private final List<CommentDto.Response> comments;

        public DetailResponse(Board board, List<CommentDto.Response> comments) {
            super(board);
            this.createdAt = board.getCreatedAt();
            this.updatedAt = board.getUpdatedAt();
            this.comments = comments;
        }
    }
}
