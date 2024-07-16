package com.sparta.kanbanssam.board.service;

import com.sparta.kanbanssam.board.dto.BoardByGuestResponseDto;
import com.sparta.kanbanssam.board.dto.BoardRequestDto;
import com.sparta.kanbanssam.board.dto.BoardResponseDto;
import com.sparta.kanbanssam.board.dto.BoardUpdateRequestDto;
import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.board.entity.Guest;
import com.sparta.kanbanssam.board.repository.BoardRepository;
import com.sparta.kanbanssam.board.repository.GuestRepository;
import com.sparta.kanbanssam.common.enums.ErrorType;
import com.sparta.kanbanssam.common.enums.UserRole;
import com.sparta.kanbanssam.common.exception.CustomException;
import com.sparta.kanbanssam.user.entity.User;
import com.sparta.kanbanssam.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final GuestRepository guestRepository;

    @Transactional
    //유저가 있는지 확인 후 없다면 Exception
    public BoardResponseDto createBoard(BoardRequestDto requestDto, User user) {
        user = userRepository.findByAccountId(user.getAccountId()).orElseThrow(()
                -> new CustomException(ErrorType.NOT_FOUND_USER));

        //유저 Role이 매니저인지 검증하는 로직
        checkUserRole(user);

        Board board = Board.builder()
                .name(requestDto.getName())
                .introduction(requestDto.getIntroduction())
                .user(user)
                .build();

        boardRepository.save(board);

        Guest guest = new Guest(user, board);
        guestRepository.save(guest);

        return new BoardResponseDto(board);
    }

    //해당 유저 보드 전체 조회
    @Transactional
    public List<BoardResponseDto> findAllBoard(User user) {
        List<Board> boardList = boardRepository.findAllByUserId(user.getId());

        if (boardList.isEmpty()) {
            throw new CustomException(ErrorType.NOT_FOUND_USER_BOARD);
        }
        return boardList.stream()
                .map(BoardResponseDto::new)
                .toList();
    }

    //보드 선택 조회
    public Board findBoard(Long boardId, User user, Guest guest) {
        Board board = boardRepository.findById(boardId).orElseThrow(()
                -> new CustomException(ErrorType.NOT_FOUND_BOARD));

        if (!board.getUser().getId().equals(user.getId()) || !board.getUser().getId().equals(guest.getUser().getId())) {
            throw new CustomException(ErrorType.BOARD_ACCESS_FORBIDDEN);
        }
//       Todo : Invite 기능구현 후 Test
//        if (invite.getUser().getUserRole().equals(UserRole.USER) && !board.getUser().equals(invite.getUser())) {
//            throw new CustomException(ErrorType.BOARD_ACCESS_FORBIDDEN);
//        }
        return board;
    }

    // 보드수정로직
    @Transactional
    public Board updateBoard(Long boardId, BoardUpdateRequestDto requestDto, User user) {
        //보드 ID 검색 후 Null 값이라면 Exception 발생
        Board board = boardRepository.findById(boardId).orElseThrow(()
                -> new CustomException(ErrorType.NOT_FOUND_BOARD));
        //유저 Role이 매니저인지 검증하는 로직
        checkUserRole(user);
        //보드에서 가져온 ID 와 User 의 ID가 다르다면 Exception 발생
        if (!board.getUser().getAccountId().equals(user.getAccountId())) {
            new CustomException(ErrorType.BOARD_ACCESS_FORBIDDEN);
        }
        // 수정
        board.updateBoard(requestDto);
        return board;
    }

    //보드 삭제
    @Transactional
    public void deleteBoard(Long boardId, User user) {

        // board 의 ID가 없다면 Exception 발생
        Board board = boardRepository.findById(boardId).orElseThrow(()
                -> new CustomException(ErrorType.NOT_FOUND_BOARD));
        //userRole이 MANAGER인지 검증
         checkUserRole(user);
        if (!board.getUser().getAccountId().equals(user.getAccountId())) {
            throw new CustomException(ErrorType.BOARD_ACCESS_FORBIDDEN);
        }
        boardRepository.delete(board);
    }

    @Transactional
    // 유저초대
    public Board inviteGuest(User user, Long boardId, List<String> emailList) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> new CustomException(ErrorType.NOT_FOUND_BOARD));

        board.validateAuthority(user);

        List<User> usertList = emailList.stream()
                .map(
                        g -> userRepository.findByEmail(g)
                                .orElseThrow(()-> new CustomException(ErrorType.NOT_FOUND_USER))
                )
                .toList();

        usertList.forEach(u ->
                {
                    guestRepository.findByBoardAndUser(board, u).ifPresent(g -> {
                        throw new CustomException(ErrorType.DUPLICATE_INVITE_USER);
                    });
                }
        );


        List<Guest> guestList = usertList.stream()
                .map(u -> new Guest(u, board))
                .toList();

        guestRepository.saveAll(guestList);

        return board;

//        User inviteUser = userRepository.findById(invitedUserId).orElseThrow(
//                () -> new CustomException(ErrorType.NOT_FOUND_USER));
//        Board board = boardRepository.findById(boardId).orElseThrow(()
//                -> new CustomException(ErrorType.NOT_FOUND_BOARD));
//        checkUserId(user, board);
//        //초대된 유저인지 확인
//        if (guestRepository.existsByUserId(invitedUserId)) {
//            throw new CustomException(ErrorType.DUPLICATE_INVITE_USER);
//        }
//        Guest guest = new Guest(inviteUser, board);
//        guestRepository.save(guest);
//        return board;
    }

    // 게스트의 보드 목록 조회
    public BoardByGuestResponseDto findAllBoardsByGuest(User user) {
        List<BoardResponseDto> boardList = guestRepository.findAllByUser(user).stream()
                .map(Guest::getBoard)
                .map(BoardResponseDto::new)
                .toList();

        return new BoardByGuestResponseDto(user.getName(), boardList);
    }

    //UserRole 이 Manager 인지 검증하는 로직
    private void checkUserRole(User user) {
        if (!user.getUserRole().equals(UserRole.MANAGER)) {
            throw new CustomException(ErrorType.NO_AUTHENTICATION);
        }
    }

    // 유저 아이디 검사
    private void checkUserId(User user, Board board) {
        if (!(user.getId().equals(board.getUser().getId()))) {
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
    }
}




























//    //보드초대
//    public Board inviteUserToBoard(Long boardId, BoardInviteRequsetDto boardInviteRequsetDto, User user) {
//        Board board = boardRepository.findById(boardId).orElseThrow(()
//                -> new CustomException(ErrorType.NOT_FOUND_BOARD));
//        //매니저인지 검증
//        checkUserRole(user);
//        userService.getUsersByEmail(invite.inviteUser());
//
//        invite.inviteUser(boardInviteRequsetDto);
//    }


