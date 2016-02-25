package by.ladyka.pass.controller;

import by.ladyka.pass.model.WebSite;
import by.ladyka.pass.service.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * This is RESTfull controller for ui part application
 * Created by Andrei Ladyka on 1/6/2016.
 */
@Controller
public class PasswordController extends ExceptionHandlerController {

    @Autowired
    PasswordService ps;

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public
    @ResponseBody
    WebSite create(Principal principal, String siteUrl, String email, String login, String myPassword, String description) {
        return ps.create(siteUrl, email, login, myPassword, description);
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public
    @ResponseBody
    List<WebSite> read(Principal principal, String query) {
        return ps.get(query);
    }

    @RequestMapping(value = "/password/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    WebSite read(Principal principal, @RequestParam("id") int id) {
        return ps.get(id);
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public
    @ResponseBody
    WebSite update(Principal principal, int id, String siteUrl, String email, String login, String myPassword, String description, HttpServletRequest httpServletRequest) {
        WebSite webSite = null;
        String method = httpServletRequest.getParameter("_method");
        switch (method) {
            case "PUT":
                webSite = create(principal, siteUrl, email, login, myPassword, description);
                break;
            case "DELETE":
                delete(principal, id);
                break;
            default:
                webSite = ps.edit(id, siteUrl, email, login, myPassword, description);
        }
        return webSite;
    }

    @RequestMapping(value = "/password", method = RequestMethod.DELETE)
    public
    @ResponseBody
    boolean delete(Principal principal, int id) {
        return ps.delete(id);
    }
}
