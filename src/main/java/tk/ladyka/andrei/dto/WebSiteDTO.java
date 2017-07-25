package tk.ladyka.andrei.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSiteDTO {
    private Long id;
    private LocalDateTime changeTime;
    private String email;
    private String login;
    private String myPassword;
    private String otherInfo;
    private String siteUrl;
}
