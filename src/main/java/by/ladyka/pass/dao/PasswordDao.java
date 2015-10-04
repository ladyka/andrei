package by.ladyka.pass.dao;

import by.ladyka.pass.model.WebSite;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 4.10.15.
 */
@Service
public class PasswordDao extends BaseDAO {

    public List<WebSite> get(String query) {
        query = "%" + query + "%";
        Disjunction d = Restrictions.disjunction();
        d.add(Restrictions.like(WebSite.COL_EMAIL,query));
        d.add(Restrictions.like(WebSite.COL_LOGIN,query));
        d.add(Restrictions.like(WebSite.COL_PASSWORD,query));
        d.add(Restrictions.like(WebSite.COL_OTHER_INFO,query));
        d.add(Restrictions.like(WebSite.COL_SITE_URL,query));
        Criteria criteria = getCriteria(WebSite.class);
        criteria.add(d);
        criteria.addOrder(Order.asc(WebSite.COL_ID));
        return criteria.list();
    }
}
