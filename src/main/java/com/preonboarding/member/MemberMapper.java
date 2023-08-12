package com.preonboarding.member;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberMapper {

    public Member toEntity(MemberDto.Signup dto, String password, List<String> roles) {
        return Member.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .password(password)
                .roles(roles)
                .build();
    }

    public MemberDto.Response toResponse(Member member) {
        return new MemberDto.Response(member.getId(), member.getEmail(), member.getNickname());
    }
}