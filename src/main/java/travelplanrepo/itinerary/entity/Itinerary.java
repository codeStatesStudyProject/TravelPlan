package travelplanrepo.itinerary.entity;

import lombok.Getter;
import travelplanrepo.board.entity.Board;
import travelplanrepo.common.auditing.BaseTime;
import travelplanrepo.utill.File.File;

import javax.persistence.*;

@Entity
@Getter
public class Itinerary extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "itinerary_id")
    private Long id;

    @ManyToOne
    private Board board;

    private long number;
    private String content;
    private String link;
    private File img;
    private String explanation;
    private int day;
}