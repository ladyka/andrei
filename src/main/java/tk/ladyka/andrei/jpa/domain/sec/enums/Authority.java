package tk.ladyka.andrei.jpa.domain.sec.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public enum Authority {
    ADMIN(1, "ADMIN", "ADMIN", "ADMIN"),
    DISABLED(1000002, "DISABLED", "This role is used to turn off a tool so noone can access it, even an administator", "2020-01-01 01:01:01"),
    ROLE_ADMIN(1000003, "ROLE_ADMIN", "Administrator Role - Full access to everything", "25-AUG-15"),
    ROLE_CONFIG_EXTENSION(1000004, "ROLE_CONFIG_EXTENSION", "Config Extension Management", "25-AUG-15"),
    ROLE_SEC(1000005, "ROLE_SEC", "Read only permissions for security tools", "25-AUG-15"),
    ROLE_SEC_ROLE(1000006, "ROLE_SEC_ROLE", "Role Management", "25-AUG-15"),
    ROLE_SEC_USER(1000007, "ROLE_SEC_USER", "User Management", "25-AUG-15"),
    ROLE_SEC_GROUP(1000008, "ROLE_SEC_GROUP", "Group Management", "25-AUG-15"),
    ROLE_SEC_TOOL(1000009, "ROLE_SEC_TOOL", "Tool Management", "25-AUG-15"),
    ROLE_CONFIG(1000010, "ROLE_CONFIG", "Configurator Admin", "02-JUN-16"),
    ROLE_APP(1000011, "ROLE_APP", "Basic User", "user");

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

//  private Set<UserAuthority> userAuthorities;


    Authority(long id, String code, String description, String name) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.name = name;
    }

    public static Authority valueOf(long authorityId) {
        for(Authority a: values()) {
            if (a.getId() == authorityId) {
                return a;
            }
        }
        throw new EnumConstantNotPresentException(Authority.class,"Incorrect Id");
    }

    public static Authority findOne(long authorityId) {
        return Arrays.stream(values())
                .filter(authority -> Objects.equals(authority.getId(), authorityId))
                .collect(Collectors.toList()).get(0);
    }
}
