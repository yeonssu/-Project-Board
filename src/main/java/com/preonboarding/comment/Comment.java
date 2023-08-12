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
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn
    private Board board;

    @ManyToOne
    @JoinColumn
    private Member member;

    public Comment(String content, Board board, Member member) {
        this.content = content;
        this.board = board;
        this.member = member;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
