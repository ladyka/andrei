package tk.ladyka.andrei.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "man")
@NoArgsConstructor
@AllArgsConstructor
public class Man {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;

    @ManyToMany(mappedBy = "men")
    private List<Event> events;

    public Man(Long id) {
        this.id = id;
    }
}
