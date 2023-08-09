package com.preonboarding.member;

import com.preonboarding.global.audit.Timestamp;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
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
}
