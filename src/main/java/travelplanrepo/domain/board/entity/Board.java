package travelplanrepo.domain.board.entity;

import lombok.*;
import travelplanrepo.domain.account.entity.Account;
import travelplanrepo.global.common.auditing.BaseTime;
import travelplanrepo.domain.itinerary.entity.Itinerary;
import travelplanrepo.domain.File.domain.File;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseTime {
    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST)
    private List<Itinerary> itineraryList;

    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST)
    private List<BoardTag> boardTagList;

    private String title;
    private String preface;
    private File thumbnail;

    @Builder
    public Board(Account account, List<Itinerary> itineraryList, List<BoardTag> boardTagList,
                 String title, String preface, File thumbnail) {
        this.account = account;
        this.itineraryList = itineraryList;
        this.boardTagList = boardTagList;
        this.title = title;
        this.preface = preface;
        this.thumbnail = thumbnail;
    }

    public void setThumbnail(File thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}