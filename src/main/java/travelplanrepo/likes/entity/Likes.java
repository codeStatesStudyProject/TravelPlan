package travelplanrepo.likes.entity;

import lombok.Data;
import lombok.Getter;
import travelplanrepo.account.entity.Account;
import travelplanrepo.board.entity.Board;
import travelplanrepo.common.auditing.BaseTime;

import javax.persistence.*;

@Entity
@Data
public class Likes extends BaseTime {
    @Id @GeneratedValue
    @Column(name = "likes_id")
    private long id;

    @ManyToOne
    private Board board;

    @ManyToOne
    private Account account;
}