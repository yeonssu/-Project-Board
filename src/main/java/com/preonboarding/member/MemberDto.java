package com.preonboarding.member;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberDto {

    @Getter
    public static class Signup {

        @Email
        private String email;

        @Pattern(regexp = "^.{8,}$", message = "비밀번호는 8자리 이상으로 작성해주세요.")
        private String password;

        @NotBlank
        private String nickname;
    }

    @Getter
    public static class Signin {

        @Email
        private String email;

        @Pattern(regexp = "^.{8,}$", message = "비밀번호는 8자리 이상으로 작성해주세요.")
        private String password;
    }

    @Getter
    public static class Response {

        private final Long id;

        private final String email;

        private final String nickname;

        public Response(Long id, String email, String nickname) {
            this.id = id;
            this.email = email;
            this.nickname = nickname;
        }
    }
}
