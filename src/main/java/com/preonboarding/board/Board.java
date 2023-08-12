package com.preonboarding.board;

import com.preonboarding.comment.Comment;
import com.preonboarding.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinColumn
    private Member member;

    @OneToMany(cascade = CascadeType.ALL)
    private final List<Comment> comments = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Board(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
        setUpdatedAt();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
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
