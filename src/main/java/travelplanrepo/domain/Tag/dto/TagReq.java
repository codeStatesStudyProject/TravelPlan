package travelplanrepo.domain.Tag.dto;

import lombok.Data;
import travelplanrepo.domain.Tag.entity.Tag;

import javax.validation.constraints.NotNull;

@Data
public class TagReq {

    @NotNull
    private String content;

    public Tag toTag() {
        return new Tag(content);
    }
}
