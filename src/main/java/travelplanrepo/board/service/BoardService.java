package travelplanrepo.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travelplanrepo.account.entity.Account;
import travelplanrepo.account.repository.AccountRepository;
import travelplanrepo.board.entity.Board;
import travelplanrepo.board.repository.BoardRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final AccountRepository accountRepository;

    public void signBoard(long accountId, Board board) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NoSuchElementException("Account를 찾을 수 없습니다."));
        board.setAccount(account);

        boardRepository.save(board);
    }

}
