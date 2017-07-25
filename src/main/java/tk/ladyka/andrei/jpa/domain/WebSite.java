package tk.ladyka.andrei.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "web_site")
public class WebSite implements Serializable{
    private static final long serialVersionUID = 201510041737011231L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime changeTime;

    private String email;

    private String login;

    private String myPassword;

    @Lob
    private String otherInfo;

    private String siteUrl;

}
