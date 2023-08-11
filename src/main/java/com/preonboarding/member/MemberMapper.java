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
        return new MemberDto.Response(member);
    }
}
