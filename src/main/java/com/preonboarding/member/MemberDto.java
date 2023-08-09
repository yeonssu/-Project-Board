package com.preonboarding.member;

import lombok.Builder;
import lombok.Getter;

public class MemberDto {

    @Getter
    public static class Signup {
        private String email;

        private String password;

        private String nickname;
    }

    @Getter
    public static class Response {
        private final Long id;

        private final String email;

        private final String nickname;

        @Builder
        public Response(Long id, String email, String nickname) {
            this.id = id;
            this.email = email;
            this.nickname = nickname;
        }
    }
}
