package by.ladyka.pass.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Ladyka Andrei on 12.1.16.
 */
@Entity
@DiscriminatorValue("bulbazavr")
public class Bulbazavr extends Animal{

    private int fb;

    public int getFb() {
        return fb;
    }

    public void setFb(int fb) {
        this.fb = fb;
    }

}
