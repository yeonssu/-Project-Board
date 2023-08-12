package com.preonboarding.comment;

import com.preonboarding.board.Board;
import com.preonboarding.board.BoardService;
import com.preonboarding.global.exception.CustomException;
import com.preonboarding.global.exception.ExceptionCode;
import com.preonboarding.member.MemberPrincipal;
import com.preonboarding.member.MemberService;
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

    private final MemberService memberService;

    public CommentDto.Response create(MemberPrincipal memberPrincipal, CommentDto.Post dto) {
        Board board = boardService.findVerifiedBoard(dto.getBoardId());
        Comment comment = commentMapper.toEntity(dto, board, memberPrincipal.getMember());
        board.addComment(comment);
        commentRepository.save(comment);
        return commentMapper.toResponse(comment);
    }

    public CommentDto.Response update(Long id, MemberPrincipal memberPrincipal, CommentDto.Patch dto) {
        Comment comment = findVerifiedComment(id);
        memberService.compareMembers(memberPrincipal.getMember(), comment.getMember());
        comment.updateComment(dto.getContent());
        return commentMapper.toResponse(comment);
    }

    public void delete(Long id, MemberPrincipal memberPrincipal) {
        Comment comment = findVerifiedComment(id);
        memberService.compareMembers(memberPrincipal.getMember(), comment.getMember());
        commentRepository.delete(comment);
    }

    private Comment findVerifiedComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionCode.COMMENT_NOT_FOUND));
    }
}