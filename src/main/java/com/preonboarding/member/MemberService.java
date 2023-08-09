package com.preonboarding.member;

import com.preonboarding.global.exception.CustomException;
import com.preonboarding.global.exception.ExceptionCode;
import com.preonboarding.global.utils.AuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityUtils authorityUtils;

    public MemberDto.Response createMember(MemberDto.Signup dto) {
        verifyExistEmail(dto.getEmail());
        verifyExistNickname(dto.getNickname());
        Member member = memberMapper.toEntity(dto);
        member.updatePassword(passwordEncoder.encode(dto.getPassword()));
        member.updateRoles(authorityUtils.createRoles());
        memberRepository.save(member);
        return memberMapper.toResponse(member);
    }

    public void verifyExistEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new CustomException(ExceptionCode.MEMBER_EMAIL_EXIST);
        }
    }

    private void verifyExistNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new CustomException(ExceptionCode.MEMBER_NICKNAME_EXIST);
        }
    }
}
