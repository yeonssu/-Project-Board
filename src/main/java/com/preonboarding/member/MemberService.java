package com.preonboarding.member;

import com.preonboarding.global.exception.CustomException;
import com.preonboarding.global.exception.ExceptionCode;
import com.preonboarding.global.utils.AuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityUtils authorityUtils;

    public MemberDto.Response create(MemberDto.Signup dto) {
        verifyExistEmail(dto.getEmail());
        verifyExistNickname(dto.getNickname());
        String password = passwordEncoder.encode(dto.getPassword());
        Member member = memberMapper.toEntity(dto, password, authorityUtils.createRoles());
        memberRepository.save(member);
        return memberMapper.toResponse(member);
    }

    public void compareMembers(Member loginMember, Member writer) {
        if (!loginMember.getEmail().equals(writer.getEmail())) {
            throw new CustomException(ExceptionCode.MEMBER_NOT_SAME);
        }
    }

    private void verifyExistEmail(String email) {
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
