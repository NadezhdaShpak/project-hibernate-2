package dao;

import entity.City;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CityDAO extends AbstractDAO<City> {
    public CityDAO(Session session) {
        super(City.class, session);
    }

    public City getByName(String name) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<City> critQuery = builder.createQuery(City.class);
        Root<City> root = critQuery.from(City.class);
        critQuery.select(root).where(builder.equal(root.get("city"), name));
        Query<City> query = session.createQuery(critQuery);
        return query.getSingleResult();
    }
}