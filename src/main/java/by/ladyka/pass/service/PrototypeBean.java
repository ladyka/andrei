package by.ladyka.pass.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class PrototypeBean {

    String scope = "Создает и возвращает новый экземляр бина на каждый запрос.";
    int count = 0;

    @Override
    public String toString() {
        count++;
        return "PrototypeBean{" +
                "count=" + count +
                ", scope='" + scope + '\'' +
                "}\n";
    }
}
