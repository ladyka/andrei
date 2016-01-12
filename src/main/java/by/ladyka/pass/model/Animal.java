package by.ladyka.pass.model;


import javax.persistence.*;

/**
 * Created by Ladyka Andrei on 12.1.16.
 */
@Entity
@Table(name = "animal")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType= DiscriminatorType.STRING)
@DiscriminatorValue(value="animal")
public class Animal {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String fa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }
}
