package travelplanrepo.domain.likes.entity;

import lombok.Builder;
import lombok.Data;
import travelplanrepo.domain.account.entity.Account;
import travelplanrepo.domain.board.entity.Board;
import travelplanrepo.global.common.auditing.BaseTime;

import javax.persistence.*;

@Entity
@Data
public class Likes extends BaseTime {
    @Id @GeneratedValue
    @Column(name = "likes_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}