package by.ladyka.pass.controller;

import by.ladyka.pass.model.WebSite;
import by.ladyka.pass.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

/**
 * Created by user on 4.10.15.
 */
@Controller
public class PassController {

    @Autowired
    PasswordService ps;

    @RequestMapping(value = "/password/put")
    public @ResponseBody
    WebSite create(Model model, Principal principal,String url, String email, String login, String myPassword, String description) {
        return ps.create(url, email, login, myPassword, description);
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public @ResponseBody
    List<WebSite> read(Model model, Principal principal,String query) {
        return ps.get(query);
    }

    @RequestMapping(value = "/password/update", method = RequestMethod.POST)
    public @ResponseBody
    WebSite updatev(Model model, Principal principal,int id, String url, String email, String login, String myPassword, String description) {
        return ps.edit(id, url, email, login, myPassword, description);
    }

    @RequestMapping(value = "/password/delete")
    public @ResponseBody
    boolean delete(Model model, Principal principal,int id) {
        return ps.delete(id);
    }



}
