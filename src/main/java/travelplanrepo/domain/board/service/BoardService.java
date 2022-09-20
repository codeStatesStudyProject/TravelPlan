package travelplanrepo.domain.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import travelplanrepo.domain.account.entity.Account;
import travelplanrepo.domain.account.repository.AccountRepository;
import travelplanrepo.domain.board.entity.Board;
import travelplanrepo.domain.board.repository.BoardRepository;

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
