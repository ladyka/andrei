package by.ladyka.pass.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Ladyka Andrei on 12.1.16.
 */
@Entity
@DiscriminatorValue("crocodile")
public class Crocodile extends Animal{

    public byte[] fc;

    public byte[] getFc() {
        return fc;
    }

    public void setFc(byte[] fc) {
        this.fc = fc;
    }
}
