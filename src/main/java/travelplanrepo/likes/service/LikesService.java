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

    public void createLikes(Likes likes) {
        Account account = accountRepository.findById(likes.getAccount().getId())
                .orElseThrow(() -> new NoSuchElementException("Account를 찾을 수 없습니다."));
        Board board = boardRepository.findById(likes.getBoard().getId())
                .orElseThrow(() -> new NoSuchElementException("Board를 찾을 수 없습니다."));

        likes.setAccount(account);
        likes.setBoard(board);

        likesRepository.save(likes);
    }

    public void deleteLikes(/*받아야되는게 잇움*/) {

    }
}
