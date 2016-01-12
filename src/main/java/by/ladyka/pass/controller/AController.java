package by.ladyka.pass.controller;

import by.ladyka.pass.ExceptionHandlerController;
import by.ladyka.pass.model.Animal;
import by.ladyka.pass.model.Bulbazavr;
import by.ladyka.pass.model.Crocodile;
import by.ladyka.pass.service.AService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ladyka Andrei on 12.1.16.
 */
@RestController
public class AController extends ExceptionHandlerController {

    @Autowired
    AService as;

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public List<Animal> read(Principal principal) {
        List<Animal> l = as.get();
        logger.info("READ MANY : " + Arrays.toString(l.toArray()));
        return l;
    }

    @RequestMapping(value = "/b", method = RequestMethod.GET)
    public List<Bulbazavr> readB(Principal principal) {
        List<Bulbazavr> l = as.getB();
        logger.info("READ MANY : " + Arrays.toString(l.toArray()));
        return l;
    }

    @RequestMapping(value = "/c", method = RequestMethod.GET)
    public List<Crocodile> readC(Principal principal) {
        List<Crocodile> l = as.getC();
        logger.info("READ MANY : " + Arrays.toString(l.toArray()));
        return l;
    }
}
