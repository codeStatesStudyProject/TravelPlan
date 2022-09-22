package travelplanrepo.likes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travelplanrepo.account.entity.Account;
import travelplanrepo.account.repository.AccountRepository;
import travelplanrepo.board.entity.Board;
import travelplanrepo.board.repository.BoardRepository;
import travelplanrepo.likes.entity.Likes;
import travelplanrepo.likes.repository.LikesRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final AccountRepository accountRepository;
    private final BoardRepository boardRepository;

    public void createLikes(Long accountId, Long boardId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NoSuchElementException("Account를 찾을 수 없습니다."));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("Board를 찾을 수 없습니다."));

        Likes likes = new Likes();
        likes.setAccount(account);
        likes.setBoard(board);

        likesRepository.save(likes);
    }

    public void deleteLikes(Long accountId, Long boardId) {
        Long findLikesId = findVerifiedLikes(accountId, boardId);
        likesRepository.deleteById(findLikesId);
    }

    private Long findVerifiedLikes(Long accountId, Long boardId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new NoSuchElementException("회원을 찾을 수 없습니다."));

        Board board = boardRepository.findById(boardId).orElseThrow(() ->
                new NoSuchElementException("게시글을 찾을 수 없습니다."));

        Likes likes = likesRepository.findLikesBy(accountId, boardId).orElseThrow(() ->
                new NoSuchElementException("좋아요를 찾을 수 없습니다."));

        return likes.getId();
    }
}
