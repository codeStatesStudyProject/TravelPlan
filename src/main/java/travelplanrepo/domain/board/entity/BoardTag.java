package travelplanrepo.domain.board.entity;

import lombok.Getter;
import travelplanrepo.domain.Tag.entity.Tag;
import travelplanrepo.global.common.auditing.BaseTime;

import javax.persistence.*;

@Entity
@Getter
public class BoardTag extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "boardTag_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
