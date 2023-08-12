package com.preonboarding.global.jwt.handler;

import com.preonboarding.global.jwt.provider.JwtTokenProvider;
import com.preonboarding.global.utils.ResponseUtils;
import com.preonboarding.member.Member;

import com.preonboarding.member.MemberDto;
import com.preonboarding.member.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class MemberAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("[MemberAuthenticationSuccessHandler] Authenticated success");
        MemberPrincipal memberPrincipal = (MemberPrincipal) authentication.getPrincipal();
        Member member = memberPrincipal.getMember();

        String accessToken = jwtTokenProvider.generateAccessToken(member.getEmail(), member.getRoles());
        jwtTokenProvider.sendAccessToken(response, accessToken);
        MemberDto.Response dto = new MemberDto.Response(member.getId(), member.getEmail(), member.getNickname());
        ResponseUtils.setResponseStatus(response, HttpServletResponse.SC_OK, dto);
    }
}
