package by.ladyka.pass.service;

//
//@Service
//@Scope(WebApplicationContext.SCOPE_GLOBAL_SESSION)
public class GlobalSessionBean {

    String scope = "";
    int count = 0;

    @Override
    public String toString() {
        count++;
        return "GlobalSessionBean{" +
                "scope='" + scope + '\'' +
                ", count=" + count +
                "}\n";
    }
}
