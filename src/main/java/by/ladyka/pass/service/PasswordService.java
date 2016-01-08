package by.ladyka.pass.service;

import by.ladyka.pass.dao.PasswordDao;
import by.ladyka.pass.email.EmailManager;
import by.ladyka.pass.model.WebSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by AndreyLadyko on 1/8/2016.
 */
@Service
public class PasswordService {

    private final String MY_EMAIL = "andrei.ladyka@live.com";
    private final String APP_NAME = "Password App ";

    @Autowired
    PasswordDao dao;

    public List<WebSite> get(String query) {
        if (query == null) {
            return dao.getEntitys(WebSite.class);
        } else {
            return dao.get(query);
        }
    }

    public WebSite create(String url, String email, String login, String password, String description) {
        WebSite w = update(new WebSite(),email,login,password,description,url);
        EmailManager.send(MY_EMAIL,APP_NAME + "[CREATE]",w.toString());
        return w;
    }

    public WebSite edit(int id, String url, String email, String login, String password, String description) {
        WebSite webSite = dao.getEntity(WebSite.class,id);
        WebSite w = update(webSite,email,login,password,description,url);
        EmailManager.send(MY_EMAIL,APP_NAME + "[UPDATE]","FROM :\n" + webSite.toString() + "\nTO :\n" + w.toString());
        return w;
    }

    public boolean delete(int id) {
        try {
            WebSite w = dao.getEntity(WebSite.class,id);
            EmailManager.send(MY_EMAIL,APP_NAME + "[DELETE]",w.toString());
            return dao.delete(new WebSite().setId(id));
        } catch (Exception ex) {
            return false;
        }
    }

    private WebSite update(WebSite webSite, String email, String login, String password, String description, String url) {
        webSite.setSiteUrl(url);
        webSite.setEmail(email);
        webSite.setLogin(login);
        webSite.setMypassword(password);
        webSite.setChangeTime(new Timestamp(System.currentTimeMillis()));
        webSite.setOtherInfo(description);
        dao.saveOrUpdate(webSite);
        return webSite;
    }
}
