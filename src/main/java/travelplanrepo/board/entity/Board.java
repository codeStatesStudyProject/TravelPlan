package travelplanrepo.board.entity;

import lombok.Getter;
import lombok.Setter;
import travelplanrepo.account.entity.Account;
import travelplanrepo.common.auditing.BaseTime;
import travelplanrepo.itinerary.entity.Itinerary;
import travelplanrepo.utill.File.File;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Board extends BaseTime {
    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne
    private Account account;

    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST)
    private List<Itinerary> itineraryList = new ArrayList<>();

    private String title;
    private String preface;
    private File thumbnail;
}