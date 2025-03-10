package dao;

import entity.Category;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CategoryDAO extends AbstractDAO<Category> {
    public CategoryDAO(Session session) {
        super(Category.class, session);
    }
    public Category getByName(String name) {
        Session session = getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> critQuery = builder.createQuery(Category.class);
        Root<Category> root = critQuery.from(Category.class);
        critQuery.select(root).where(builder.equal(root.get("name"), name));
        Query<Category> query = session.createQuery(critQuery);
        return query.getSingleResult();
    }
}