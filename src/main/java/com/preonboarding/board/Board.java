package com.preonboarding.board;

import com.preonboarding.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Board {

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

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

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
        setUpdatedAt();
    }

    public void addViewCount() {
        this.viewCount += 1;
    }

    public void addLikeCount() {
        likeCount += 1;
    }

    private void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
