package travelplanrepo.domain.likes.entity;

import lombok.Getter;
import travelplanrepo.domain.account.entity.Account;
import travelplanrepo.domain.board.entity.Board;
import travelplanrepo.global.common.auditing.BaseTime;

import javax.persistence.*;

@Entity
@Getter
public class Likes extends BaseTime {
    @Id @GeneratedValue
    @Column(name = "likes_id")
    private long id;

    @ManyToOne
    private Board board;

    @ManyToOne
    private Account account;
}