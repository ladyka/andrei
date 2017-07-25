package tk.ladyka.andrei.jpa.domain.sec;

import lombok.Data;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Data
public class ToolGroup implements Serializable {

    private long id;
    private String code;
    private String iconName;
    private String name;
    private int sortOrder;
    private Set<Tool> tools = new HashSet<>();

    private ToolGroup(long id, String code, String iconName, String name, int sortOrder) {
        this.id = id;
        this.code = code;
        this.iconName = iconName;
        this.name = name;
        this.sortOrder = sortOrder;
    }

    public static ToolGroup[] values() {
        return new ToolGroup[]{new ToolGroup(2, "config", "folder", "Configurator Tools", 20),
                new ToolGroup(3, "security", "folder", "Security Administration", 990),
                new ToolGroup(4, "app", "folder", "Applications", 10)};
    }

    public static ToolGroup[] valuesCopy() {
        return Arrays.copyOf(values(), values().length);
    }

    public void addTool(Tool tool) {
        tools.add(tool);
    }

    public static ToolGroup findOne(long toolGroupId) {
        List<ToolGroup> toolGroups = Arrays.stream(values())
                .filter(toolGroup -> Objects.equals(toolGroup.getId(), toolGroupId))
                .collect(Collectors.toList());
        return getToolGroupOneValue(toolGroups);
    }

    private static ToolGroup getToolGroupOneValue(List<ToolGroup> toolGroups) {
        ToolGroup toolGroup = null;
        if (toolGroups.size() > 0) {
            toolGroup = toolGroups.get(0);
        }
        return toolGroup;
    }
}
