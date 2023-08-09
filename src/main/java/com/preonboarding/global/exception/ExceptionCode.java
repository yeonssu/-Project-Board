package com.preonboarding.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    MEMBER_EMAIL_EXIST(409, "이미 사용중인 이메일 입니다."),
    MEMBER_NICKNAME_EXIST(409, "이미 사용중인 닉네임 입니다.");

    private final int code;

    private final String message;
}
