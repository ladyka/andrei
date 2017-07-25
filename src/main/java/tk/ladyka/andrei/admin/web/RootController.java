package tk.ladyka.andrei.admin.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {

    /**
     * This is the base entry point to the Admin application. It just checks to see that the user is valid and logged in
     *
     * @param model          Spring supplied Model object
     * @param authentication Spring supplied Authentication object
     * @return The home page template name
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());

        return "home";
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String events(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        return "event";
    }

    @RequestMapping(value = "/passwords", method = RequestMethod.GET)
    public String pass(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        return "passwords";
    }

}
