package travelplanrepo.likes.dto;

import lombok.Data;
import travelplanrepo.account.entity.Account;
import travelplanrepo.board.entity.Board;
import travelplanrepo.likes.entity.Likes;

@Data
public class PostLikesDto {
    private Long boardId;
    private Long accountId;

    public Likes toLikes() {
        Board board = new Board();
        board.setId(boardId);

        Account account = new Account();
        account.setId(accountId);

        Likes likes = new Likes();
        likes.setBoard(board);
        likes.setAccount(account);

        return likes;
    }
}
