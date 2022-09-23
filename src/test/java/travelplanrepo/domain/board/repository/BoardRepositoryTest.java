package travelplanrepo.domain.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import travelplanrepo.domain.account.entity.Account;
import travelplanrepo.domain.account.repository.AccountRepository;
import travelplanrepo.domain.board.entity.Board;
import travelplanrepo.domain.itinerary.entity.Itinerary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void boardTest() {
        Board board = Board.builder()
                .title("hello")
                .itineraryList(new ArrayList<>())
                .build();
        Itinerary itinerary = new Itinerary();
        itinerary.chaneBoard(board);

        boardRepository.save(board);
        List<Board> all = boardRepository.findAll();

        List<Account> all1 = accountRepository.findAll();
//        try {
//            throw new Exception("Test Error");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
}
