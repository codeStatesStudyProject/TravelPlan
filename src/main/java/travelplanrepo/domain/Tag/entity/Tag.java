package travelplanrepo.domain.Tag.entity;

import lombok.Getter;
import travelplanrepo.global.common.auditing.BaseTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Tag extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    private String content;
}
