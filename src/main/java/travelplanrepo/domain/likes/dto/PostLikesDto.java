package travelplanrepo.domain.likes.dto;

import lombok.Data;
import travelplanrepo.domain.account.entity.Account;
import travelplanrepo.domain.board.entity.Board;
import travelplanrepo.domain.likes.entity.Likes;

@Data
public class PostLikesDto {
    private Long boardId;
    private Long accountId;

    public Likes toLikes() {
        Board board = Board.builder().id(boardId).build();

        Account account = new Account();
        account.setId(accountId);

        Likes likes = new Likes();
        likes.setBoard(board);
        likes.setAccount(account);

        return likes;
    }
}
