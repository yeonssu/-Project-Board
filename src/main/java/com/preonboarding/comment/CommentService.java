package com.preonboarding.comment;

import com.preonboarding.board.BoardService;
import com.preonboarding.member.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    private final BoardService boardService;

    public CommentDto.Response createComment(Long boardId, MemberPrincipal memberPrincipal, CommentDto.Post dto) {
        Comment comment = commentMapper.toEntity(dto);
        comment.updateBoard(boardService.findVerifiedBoard(boardId));
        comment.updateMember(memberPrincipal.getMember());
        commentRepository.save(comment);
        return commentMapper.toResponse(comment);
    }
}