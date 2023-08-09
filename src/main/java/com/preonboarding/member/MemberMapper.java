package com.preonboarding.member;

import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member toEntity(MemberDto.Signup dto) {
        return Member.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .build();
    }

    public MemberDto.Response toResponse(Member member) {
        return MemberDto.Response.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
