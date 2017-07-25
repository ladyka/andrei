package tk.ladyka.andrei.jpa.domain.sec;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Tool {
    EXTENSION(2, "BaseToolController", "extension", "extension", "Config Extensions", 100, 1000004, 2),
    ROLES(3, "BaseToolController", "roles", "verified_user", "Role Admin", 10, 1000006, 3),
    USERS(4, "UsersListController", "users", "face", "User", 20, 1000007, 3),
    GROUPS(5, "BaseToolController", "groups", "group", "Group Admin", 30, 1000008, 3),
    TOOLS(6, "ToolsGroupsController", "tools", "build", "Tools", 40, 1000009, 3),
    DISABLE_TEST(7, "BaseToolController", "disable_test", "test", "Disable role", 50, 1000002, 3),
    CFG_TRIM(8, "TrimsListController", "cfg_trim", "directions_car", "Trims", 30, 1000010, 2),
    CFG_PART(9, "BaseToolController", "cfg_part", "widgets", "Parts", 20, 1000010, 2),
    CFG_USE(11, "BaseToolController", "cfg_use", "devices_other", "Uses", 10, 1000010, 2),
    WEB_PASS(12, "PasswordController", "web_pass", "devices_other", "Password manager", 10, 1000011, 4),
    WEB_EVENT(13, "EventController", "web_event", "devices_other", "Events", 10, 1000011, 4);

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String angularController;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String iconName;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private long authorityId;

    @Getter
    @Setter
    private long toolGroupId;

    @Getter
    @Setter
    private Integer sortOrder;

    private Set<ToolFile> toolFiles;

    Tool(long id, String angularController, String code, String iconName, String name, Integer sortOrder, long authorityId, long toolGroupId) {
        this.id = id;
        this.angularController = angularController;
        this.code = code;
        this.iconName = iconName;
        this.name = name;
        this.authorityId = authorityId;
        this.toolGroupId = toolGroupId;
        this.sortOrder = sortOrder;
    }

    public tk.ladyka.andrei.jpa.domain.sec.enums.Authority getAuthority() {
        return tk.ladyka.andrei.jpa.domain.sec.enums.Authority.valueOf(authorityId);
    }

    public ToolGroup getToolGroup() {
        return ToolGroup.findOne(toolGroupId);
    }

    public static List<Tool> getTools(final long authorityId) {
        return Arrays.stream(values())
                .filter(tool -> tool.getAuthorityId() == authorityId)
                .collect(Collectors.toList());
    }

    public static Tool findByCode(String toolCode) {
        return Arrays.stream(values())
                .filter(tool -> Objects.equals(tool.getCode(), toolCode))
                .collect(Collectors.toList()).get(0);
    }

    public static Tool findOne(long toolId) {
        return Arrays.stream(values())
                .filter(tool -> Objects.equals(tool.getId(), toolId))
                .collect(Collectors.toList()).get(0);
    }
}
