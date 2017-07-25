package tk.ladyka.andrei.jpa.domain.sec;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ToolFile {
    TMPL_SECURITY_USERS_LIST(1,"_tmpl/security/users/list.html",4,2),
    JS_SECURITY_USERS_USERS(2,"_js/security/users/users.js",4,3),
    TMPL_SECURITY_TOOLS_GROUPS(3,"_tmpl/security/tools/groups.html",6,2),
    JS_SECURITY_TOOLS_GROUPS(4,"_js/security/tools/groups.js",6,3),
    JS_SECURITY_SERVICES(5,"_js/security/services.js",4,3),
    TMPL_CONFIGURATOR_TRIM_LIST(6,"_tmpl/configurator/trim/list.html",8,2),
    JS_CONFIGURATOR_TRIM_TRIMS(7,"_js/configurator/trim/trims.js",8,3);

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String resourcePath;

    @Getter
    @Setter
    private long toolId;

    @Getter
    @Setter
    private ToolFileType type;

    ToolFile(long id, String resourcePath, long toolId, long toolFileTypeId) {
        this(id,resourcePath,toolId,ToolFileType.valueOf(toolFileTypeId));
    }

    ToolFile(long id, String resourcePath, long toolId, ToolFileType type) {
        this.id = id;
        this.resourcePath = resourcePath;
        this.toolId = toolId;
        this.type = type;
    }
}
