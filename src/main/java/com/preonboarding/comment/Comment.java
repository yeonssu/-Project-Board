package com.preonboarding.comment;

import com.preonboarding.global.audit.Timestamp;
import com.preonboarding.board.Board;
import com.preonboarding.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Comment(String content) {
        this.content = content;
    }

    public void updateBoard(Board board) {
        this.board = board;
    }

    public void updateMember(Member member) {
        this.member = member;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
