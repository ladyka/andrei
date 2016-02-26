package by.ladyka.pass.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class SingletonBean {

    int count = 0;
    String scope = "Возвращает один и тот же экземляр бина на каждый запрос контейнера Spring IoC (по умолчанию).";


    @Override
    public String toString() {
        count++;
        return "SingletonBean{" +
                "count=" + count +
                ", scope='" + scope + '\'' +
                "}\n";
    }
}
