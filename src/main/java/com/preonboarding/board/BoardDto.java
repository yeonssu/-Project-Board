package com.preonboarding.board;

import lombok.Builder;
import lombok.Getter;

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

        @Builder
        public Response(Long id, String title, String content, Long viewCount, Long likeCount, String nickname) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.viewCount = viewCount;
            this.likeCount = likeCount;
            this.nickname = nickname;
        }
    }
}
