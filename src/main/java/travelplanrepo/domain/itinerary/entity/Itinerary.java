package travelplanrepo.domain.itinerary.entity;

import lombok.Getter;
import lombok.Setter;
import travelplanrepo.domain.board.entity.Board;
import travelplanrepo.global.common.auditing.BaseTime;
import travelplanrepo.domain.File.domain.File;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Itinerary extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "itinerary_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private long number;
    private String title;
    private String link;
    private File img;
    private String explanation;
    private int day;

    public void chaneBoard(Board board) {
        this.board = board;
        board.getItineraryList().add(this);
    }
}