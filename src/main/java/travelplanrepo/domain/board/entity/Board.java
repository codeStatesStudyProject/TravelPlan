package travelplanrepo.domain.board.entity;

import lombok.Getter;
import lombok.Setter;
import travelplanrepo.domain.account.entity.Account;
import travelplanrepo.global.common.auditing.BaseTime;
import travelplanrepo.domain.itinerary.entity.Itinerary;
import travelplanrepo.domain.File.File;

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