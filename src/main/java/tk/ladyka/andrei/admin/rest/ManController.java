package tk.ladyka.andrei.admin.rest;

import tk.ladyka.andrei.dto.ManDTO;
import tk.ladyka.andrei.utils.spring.impl.ManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/api/man")
public class ManController {

    @Autowired
    ManService manService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public
    @ResponseBody
    ManDTO createOrEdit(Principal principal, ManDTO man) {
        final String userEmail = (principal != null) ? principal.getName() : null;
        return manService.createOrEdit(man, userEmail);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public
    @ResponseBody
    List<ManDTO> read(Principal principal, String query) {
        final String userEmail = (principal != null) ? principal.getName() : null;
        return manService.get(query, userEmail);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    ManDTO read(Principal principal, @RequestParam("id") Long id) {
        final String userEmail = (principal != null) ? principal.getName() : null;
        return manService.get(id, userEmail);
    }
}
