package by.ladyka.pass.controller;

import by.ladyka.pass.ExceptionHandlerController;
import by.ladyka.pass.model.WebSite;
import by.ladyka.pass.service.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrei Ladyka on 1/6/2016.
 */
@RestController
public class PasswordController extends ExceptionHandlerController {

    @Autowired
    PasswordService ps;

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public WebSite create(Principal principal, String siteUrl, String email, String login, String myPassword, String description) {
        WebSite webSite = new WebSite().setId(-1);
        logger.info("CREATE : " + webSite);
        return webSite; //ps.create(siteUrl, email, login, myPassword, description);
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public List<WebSite> read(Principal principal, String query) {
        //List<WebSite> l = ps.get(query);
        List<WebSite> l = new ArrayList<>();
        WebSite w = new WebSite().setId(-1);
        w.setSiteUrl(ps.toString());
        w.setOtherInfo("oi");
        w.setMypassword("mp");
        w.setLogin("l");
        w.setMypassword("p");
        w.setChangeTime(new Timestamp(System.currentTimeMillis()));
        l.add(w);
        logger.info("READ : " + Arrays.toString(l.toArray()));
        return l;
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public WebSite update(Principal principal, int id, String siteUrl, String email, String login, String myPassword, String description) {
        WebSite webSite = new WebSite().setId(-1);
        logger.info("UPDATE : " + webSite);
        return webSite; //ps.edit(id, siteUrl, email, login, myPassword, description);
    }

    @RequestMapping(value = "/password", method = RequestMethod.DELETE)
    public boolean delete(Principal principal, int id) {
        WebSite webSite = new WebSite().setId(-1);
        logger.info("DELETE : " + webSite);
        return false; //ps.delete(id);
    }
}
