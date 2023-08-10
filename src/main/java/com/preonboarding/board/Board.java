package com.preonboarding.board;

import com.preonboarding.global.audit.Timestamp;
import com.preonboarding.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
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

    @Builder
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateMember(Member member) {
        this.member = member;
    }

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
