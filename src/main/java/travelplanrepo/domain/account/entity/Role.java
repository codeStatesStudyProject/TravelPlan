package travelplanrepo.domain.account.entity;

import lombok.Getter;

@Getter
public enum Role {
    USER("ROLE_USER");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }
}
