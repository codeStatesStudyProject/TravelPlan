package travelplanrepo.domain.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import travelplanrepo.domain.Tag.entity.Tag;
import travelplanrepo.global.common.auditing.BaseTime;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class BoardTag extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "boardTag_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public BoardTag(Board board, Tag tag) {
        this.board = board;
        this.tag = tag;

        board.getBoardTagList().add(this);
    }

    public void changeBoard(Board board) {
        this.board = board;
        board.getBoardTagList().add(this);
    }
}
