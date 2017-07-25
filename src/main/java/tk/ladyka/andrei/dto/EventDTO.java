package tk.ladyka.andrei.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDTO {
    private Long id;
    private String description;
    private LocalDateTime time;
}
