package by.ladyka.pass.service;

//
//@Service
//@Scope("session")
public class SessionBean {

    String scope = "Создает и возвращает экземляр бина для каждой HTTP сессии*.";
    int count = 0;

    @Override
    public String toString() {
        count++;
        return "SessionBean{" +
                "scope='" + scope + '\'' +
                ", count=" + count +
                "}\n";
    }
}
