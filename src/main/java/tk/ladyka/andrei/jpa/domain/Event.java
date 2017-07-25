package tk.ladyka.andrei.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "event")
public class Event {

    @Id
    private Long id;
    private String description;
    private LocalDateTime time;


    @ManyToMany
    @JoinTable(name = "event_has_man", joinColumns = {
            @JoinColumn(name = "Event_id", nullable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "Man_id", nullable = false)
    })
    private Set<Man> men;
}
