package by.ladyka.pass.controller;

import by.ladyka.pass.ExceptionHandlerController;
import by.ladyka.pass.model.WebSite;
import by.ladyka.pass.service.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

/**
 * This is RESTfull controller for ui part application
 * Created by Andrei Ladyka on 1/6/2016.
 */
@RestController
public class PasswordController extends ExceptionHandlerController {

    @Autowired
    PasswordService ps;

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public WebSite create(Principal principal, String siteUrl, String email, String login, String myPassword, String description) {
        WebSite webSite = ps.create(siteUrl, email, login, myPassword, description);
        logger.info("CREATE : " + webSite);
        return webSite;
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public List<WebSite> read(Principal principal, String query) {
        List<WebSite> l = ps.get(query);
        logger.info("READ MANY : " + Arrays.toString(l.toArray()));
        return l;
    }

    @RequestMapping(value = "/password/{id}", method = RequestMethod.GET)
    public WebSite read(Principal principal, @RequestParam("id") int id) {
        WebSite w = ps.get(id);
        logger.info("READ ONE : " + w.toString());
        return w;
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public WebSite update(Principal principal, int id, String siteUrl, String email, String login, String myPassword, String description) {
        WebSite webSite= ps.edit(id, siteUrl, email, login, myPassword, description);
        logger.info("UPDATE : " + webSite);
        return webSite; //
    }

    @RequestMapping(value = "/password", method = RequestMethod.DELETE)
    public boolean delete(Principal principal, int id) {
        Boolean deleteResult = ps.delete(id);
        logger.info("DELETE : " + id + " " + deleteResult);
        return deleteResult;
    }
}
