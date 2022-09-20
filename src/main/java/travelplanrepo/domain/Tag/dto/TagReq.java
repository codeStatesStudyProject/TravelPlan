package travelplanrepo.domain.Tag.dto;

import lombok.Data;
import travelplanrepo.domain.Tag.entity.Tag;

@Data
public class TagReq {
    private String content;

    public Tag toTag() {
        return new Tag(content);
    }
}
