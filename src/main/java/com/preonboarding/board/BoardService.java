package com.preonboarding.board;

import com.preonboarding.comment.*;
import com.preonboarding.global.exception.CustomException;
import com.preonboarding.global.exception.ExceptionCode;
import com.preonboarding.member.MemberPrincipal;
import com.preonboarding.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    private final BoardRepository boardRepository;

    private final MemberService memberService;

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    public BoardDto.Response create(BoardDto.Request dto, MemberPrincipal memberPrincipal) {
        Board board = boardMapper.toEntity(dto, memberPrincipal.getMember());
        boardRepository.save(board);
        return boardMapper.toResponse(board);
    }

    public Page<BoardDto.Response> getAll(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return boardMapper.toPageResponse(boards);
    }

    public BoardDto.DetailResponse get(Long id) {
        Board board = findVerifiedBoard(id);
        board.addViewCount();
        List<CommentDto.Response> comments = getCommentsOfBoard(id);
        return boardMapper.toDetailResponse(board, comments);
    }

    public BoardDto.Response update(Long boardId, BoardDto.Request dto, MemberPrincipal memberPrincipal) {
        Board board = findVerifiedBoard(boardId);
        memberService.compareMembers(memberPrincipal.getMember(), board.getMember());
        board.updateBoard(dto.getTitle(), dto.getContent());
        return boardMapper.toResponse(board);
    }

    public void delete(Long id, MemberPrincipal memberPrincipal) {
        Board board = findVerifiedBoard(id);
        memberService.compareMembers(memberPrincipal.getMember(), board.getMember());
        boardRepository.delete(board);
    }

    public void like(Long id) {
        Board board = findVerifiedBoard(id);
        board.addLikeCount();
    }

    public Board findVerifiedBoard(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionCode.BOARD_NOT_FOUND));
    }

    private List<CommentDto.Response> getCommentsOfBoard(Long boardId) {
        List<Comment> comments = commentRepository.findAllByBoard_Id(boardId);
        return commentMapper.toListResponse(comments);
    }
}
