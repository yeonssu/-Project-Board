package com.preonboarding.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    MEMBER_EMAIL_EXIST(409, "이미 사용중인 이메일 입니다."),
    MEMBER_NICKNAME_EXIST(409, "이미 사용중인 닉네임 입니다."),
    BOARD_NOT_FOUND(404, "게시글을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(404, "댓글을 찾을 수 없습니다."),
    MEMBER_NOT_SAME(403, "작성자만 삭제 및 수정이 가능합니다.");

    private final int code;

    private final String message;
}
