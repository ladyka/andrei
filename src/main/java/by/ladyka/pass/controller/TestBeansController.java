package by.ladyka.pass.controller;

import by.ladyka.pass.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class TestBeansController {

    @Autowired
    SingletonBean singletonBean;

    @Autowired
    SingletonBean singletonBean2;

    @Autowired
    PrototypeBean prototypeBean;

    @Autowired
    PrototypeBean prototypeBean2;

//    @Autowired
//    RequestBean requestBean;

//    @Autowired
//    SessionBean sessionBean;

//    @Autowired
//    GlobalSessionBean globalSessionBean;


    @RequestMapping(value = "/beans/test", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public
    @ResponseBody
    String singletonBean(Principal principal) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <10; i++) {
            stringBuilder.append(singletonBean.toString());
            stringBuilder.append(singletonBean2.toString());
            stringBuilder.append(prototypeBean.toString());
            stringBuilder.append(prototypeBean2.toString());
//            stringBuilder.append(requestBean.toString());
//            stringBuilder.append(sessionBean.toString());
//            stringBuilder.append(globalSessionBean.toString());
            stringBuilder.append("===============================================\n");
        }
        return stringBuilder.toString();
    }


}
