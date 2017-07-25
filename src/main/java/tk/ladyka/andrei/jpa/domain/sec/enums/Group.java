package tk.ladyka.andrei.jpa.domain.sec.enums;

import lombok.Getter;
import lombok.Setter;
import tk.ladyka.andrei.jpa.domain.sec.GroupUser;

import java.util.Set;

public enum Group {

    ADMIN(1, "ADMIN", "ADMIN", "ADMIN"),
    ROLE_SEC(2, "ROLE_SEC", "ROLE_SEC", "ROLE_SEC"),
    ROLE_SEC_TOOL(3, "ROLE_SEC_TOOL", "ROLE_SEC_TOOL", "ROLE_SEC_TOOL"),
    ROLE_CONFIG(4, "ROLE_CONFIG", "ROLE_CONFIG", "ROLE_CONFIG"),
    ROLE_SEC_GROUP(5, "ROLE_SEC_GROUP", "ROLE_SEC_GROUP", "ROLE_SEC_GROUP"),
    ROLE_SEC_USER(6, "ROLE_SEC_USER", "ROLE_SEC_USER", "ROLE_SEC_USER");

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String name;
    private Set<GroupAuthority> groupAuthorities;
    private Set<GroupUser> groupUsers;

    Group(long id, String code, String description, String name) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.name = name;
    }
}
