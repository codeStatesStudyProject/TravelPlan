package travelplanrepo.account.entity;

import lombok.Getter;

@Getter
public enum Role {
    User("ROLE_USER");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }
}
