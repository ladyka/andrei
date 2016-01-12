package by.ladyka.pass.service;

import by.ladyka.pass.dao.ADao;
import by.ladyka.pass.model.Animal;
import by.ladyka.pass.model.Bulbazavr;
import by.ladyka.pass.model.Crocodile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ladyka Andrei on 12.1.16.
 */
@Service
public class AService {

    @Autowired
    ADao ad;

    public List<Animal> get() {
        return ad.getEntitys(Animal.class);
    }

    public List<Bulbazavr> getB() {
        return ad.getEntitys(Bulbazavr.class);
    }

    public List<Crocodile> getC() {
        return ad.getEntitys(Crocodile.class);
    }
}
