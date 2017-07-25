package tk.ladyka.andrei.jpa.domain.sec;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ToolFileType {

    TEMPLATE(2,"template","Angular Template"),
    JAVA_SCRIPT(3,"javascript","Javascript"),
    CSS(4,"css","CSS");

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String name;

    ToolFileType(long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public static ToolFileType valueOf(final long toolFileType) {
        for(ToolFileType type : ToolFileType.values()) {
            if (type.getId() == toolFileType) {
                return type;
            }
        }
        throw new EnumConstantNotPresentException(ToolFileType.class,"Incorrect Id");
    }
}
