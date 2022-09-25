package travelplanrepo.domain.likes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travelplanrepo.domain.account.entity.Account;
import travelplanrepo.domain.account.repository.AccountRepository;
import travelplanrepo.domain.board.entity.Board;
import travelplanrepo.domain.board.repository.BoardRepository;
import travelplanrepo.domain.likes.entity.Likes;
import travelplanrepo.domain.likes.repository.LikesRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final AccountRepository accountRepository;
    private final BoardRepository boardRepository;

    public void createLikes(Long accountId, Long boardId) {
        Account account = verifiedAccount(accountId);
        Board board = verifiedBoard(boardId);

        Likes likes =new Likes();
        likes.setAccount(account);
        likes.setBoard(board);

        likesRepository.save(likes);
    }

    public void deleteLikes(Long accountId, Long boardId) {
        Likes likes = verifiedLikes(accountId, boardId);
        likesRepository.deleteById(likes.getId());
    }

    private Account verifiedAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NoSuchElementException("회원을 찾을 수 없습니다."));

        return account;
    }

    private Board verifiedBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("게시물을 찾을 수 없습니다."));

        return board;
    }

    private Likes verifiedLikes(Long accountId, Long boardId) {
        verifiedAccount(accountId);
        verifiedBoard(boardId);

        Likes likes = likesRepository.findLikesBy(accountId, boardId)
                .orElseThrow(() -> new NoSuchElementException("좋아요를 찾을 수 없습니다."));

        return likes;
    }
}
