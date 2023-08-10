package com.preonboarding.comment;

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

    public CommentDto.Response createComment(Long boardId, MemberPrincipal memberPrincipal, CommentDto.Post dto) {
        Comment comment = commentMapper.toEntity(dto);
        comment.updateBoard(boardService.findVerifiedBoard(boardId));
        comment.updateMember(memberPrincipal.getMember());
        commentRepository.save(comment);
        return commentMapper.toResponse(comment);
    }

    public CommentDto.Response modifyComment(Long commentId, MemberPrincipal memberPrincipal, CommentDto.Patch dto) {
        Comment comment = findVerifyComment(commentId);
        memberService.verifySameMember(memberPrincipal.getMember(), comment.getMember());
        comment.updateComment(dto.getContent());
        return commentMapper.toResponse(comment);
    }

    public void deleteComment(Long commentId, MemberPrincipal memberPrincipal) {
        Comment comment = findVerifyComment(commentId);
        memberService.verifySameMember(memberPrincipal.getMember(), comment.getMember());
        commentRepository.delete(comment);
    }

    private Comment findVerifyComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ExceptionCode.COMMENT_NOT_FOUND));
    }
}