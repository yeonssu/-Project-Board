package com.preonboarding.board;

import com.preonboarding.global.exception.CustomException;
import com.preonboarding.global.exception.ExceptionCode;
import com.preonboarding.member.MemberPrincipal;
import com.preonboarding.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    private final BoardRepository boardRepository;

    private final MemberService memberService;

    public BoardDto.Response createBoard(BoardDto.Post dto, MemberPrincipal memberPrincipal) {
        Board board = boardMapper.toEntity(dto);
        board.updateMember(memberPrincipal.getMember());
        boardRepository.save(board);
        return boardMapper.toResponse(board);
    }

    public Page<BoardDto.Response> getBoards(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return boardMapper.toPageResponse(boards);
    }

    public BoardDto.Response getBoard(Long id) {
        Board board = findVerifiedBoard(id);
        board.addViewCount();
        return boardMapper.toResponse(board);
    }

    public BoardDto.Response modifyBoard(Long id, BoardDto.Patch dto, MemberPrincipal memberPrincipal) {
        Board board = findVerifiedBoard(id);
        memberService.verifySameMember(memberPrincipal.getMember(), board.getMember());
        board.updateBoard(dto.getTitle(), dto.getContent());
        return boardMapper.toResponse(board);
    }

    public void deleteBoard(Long id, MemberPrincipal memberPrincipal) {
        Board board = findVerifiedBoard(id);
        memberService.verifySameMember(memberPrincipal.getMember(), board.getMember());
        boardRepository.delete(board);
    }

    private Board findVerifiedBoard(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionCode.BOARD_NOT_FOUND));
    }
}
