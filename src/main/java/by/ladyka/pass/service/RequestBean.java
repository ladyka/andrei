package by.ladyka.pass.service;

//
//@Service
//@Scope("request")
public class RequestBean {

    String scope = "Создает и возвращает экземляр бина на каждый HTTP запрос*.";
    int count = 0;

    @Override
    public String toString() {
        count++;
        return "RequestBean{" +
                "scope='" + scope + '\'' +
                ", count=" + count +
                "}\n";
    }
}
