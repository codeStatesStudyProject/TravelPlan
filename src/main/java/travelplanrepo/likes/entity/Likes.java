package travelplanrepo.likes.entity;

import lombok.Getter;
import travelplanrepo.account.entity.Account;
import travelplanrepo.board.entity.Board;
import travelplanrepo.common.auditing.BaseTime;
import travelplanrepo.common.enums.Status;

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

    @Enumerated(EnumType.STRING)
    private Status status;
}