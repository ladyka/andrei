package tk.ladyka.andrei.admin.rest.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.ladyka.andrei.jpa.domain.sec.User;
import tk.ladyka.andrei.jpa.repository.sec.UserRepository;

@Controller
public class RestoreDatabaseConnection {

    Logger logger = LoggerFactory.getLogger(RestoreDatabaseConnection.class);

    @Autowired
    private UserRepository userRepo;

    @RequestMapping("/ololo/123")
    public @ResponseBody
    String request() {
        try {
            User secUser = userRepo.findByUsernameIgnoreCase("user" + System.currentTimeMillis());
            logger.warn(secUser.toString());
            return "done";
        } catch (Exception ex) {
            logger.warn("Try to restore !!!");
            return "done1";
        }
    }
}
