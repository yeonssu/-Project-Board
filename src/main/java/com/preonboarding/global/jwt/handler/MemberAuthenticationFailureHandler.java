package com.preonboarding.global.jwt.handler;

import com.preonboarding.global.dto.ErrorResponse;
import com.preonboarding.global.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MemberAuthenticationFailureHandler implements AuthenticationFailureHandler {

    public static final String ACCOUNT_NOT_FOUND = "계정이 존재하지 않습니다. 회원가입 후 로그인해주세요.";
    public static final String CREDENTIAL_NOT_FOUND = "아이디 또는 비밀번호가 일치하지 않습니다.";
    public static final String UNKNOWN_LOGIN_ERROR = "알 수 없는 이유로 로그인에 실패했습니다.";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        log.error("[MemberAuthenticationFailureHandler] Authentication failed : {}", exception.getMessage());
        String errorMessage = getErrorMessage(exception);
        sendErrorResponse(response, errorMessage);
    }

    private String getErrorMessage(AuthenticationException exception) {
        if (exception instanceof UsernameNotFoundException) return ACCOUNT_NOT_FOUND;
        if (exception instanceof BadCredentialsException) return CREDENTIAL_NOT_FOUND;
        return UNKNOWN_LOGIN_ERROR;
    }

    private void sendErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.UNAUTHORIZED, errorMessage);
        ResponseUtils.setResponseStatus(response, HttpServletResponse.SC_UNAUTHORIZED, errorResponse);
    }
}
