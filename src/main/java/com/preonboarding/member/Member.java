package com.preonboarding.member;

import com.preonboarding.global.audit.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 45, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 45, unique = true)
    private String nickname;

    @Builder
    public Member(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}

