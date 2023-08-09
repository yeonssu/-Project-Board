package com.preonboarding.board;

import com.preonboarding.global.audit.Timestamp;
import com.preonboarding.member.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Board extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Long viewCount = 0L;

    @Column(nullable = false)
    private Long likeCount = 0L;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
