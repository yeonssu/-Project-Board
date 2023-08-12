package com.preonboarding.member;

import com.preonboarding.global.audit.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 45, unique = true)
    private String nickname;

    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 10)
    private List<String> roles = new ArrayList<>();

    @Builder
    public Member(String email, String nickname, String password, List<String> roles) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.roles = roles;
    }
}
