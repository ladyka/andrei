package tk.ladyka.andrei.admin.rest;

import tk.ladyka.andrei.dto.WebSiteDTO;
import tk.ladyka.andrei.utils.spring.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This is RESTfull controller for ui part of the application
 * Created by Andrei Ladyka on 1/6/2016.
 */
@Controller
public class PasswordController {

    @Autowired
    private PasswordService ps;

    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public
    @ResponseBody
    WebSiteDTO create(Principal principal, String siteUrl, String email, String login, String myPassword, String description) {
        return ps.create(new WebSiteDTO(null, LocalDateTime.now(),email,login,myPassword,description,siteUrl));
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public
    @ResponseBody
    List<WebSiteDTO> read(Principal principal, String query) {
        return ps.get(query);
    }

    @RequestMapping(value = "/password/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    WebSiteDTO read(Principal principal, @RequestParam("id") Long id) {
        return ps.get(id);
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public
    @ResponseBody
    WebSiteDTO update(Principal principal, Long id, String siteUrl, String email, String login, String myPassword, String description, HttpServletRequest httpServletRequest) {
        WebSiteDTO WebSiteDTO = null;
        if (new Long(0L).equals(id)) {
            id = null;
        }
        String method = httpServletRequest.getParameter("_method");
        switch (method) {
            case "PUT":
                WebSiteDTO = create(principal, siteUrl, email, login, myPassword, description);
                break;
            case "DELETE":
                delete(principal, id);
                break;
            default:
                WebSiteDTO = ps.edit(new WebSiteDTO(id, LocalDateTime.now(),email,login,myPassword,description,siteUrl));
        }
        return WebSiteDTO;
    }

    @RequestMapping(value = "/password", method = RequestMethod.DELETE)
    public
    @ResponseBody
    boolean delete(Principal principal, Long id) {
        return ps.delete(id);
    }
}
