package dao;

import entity.Rental;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class RentalDAO extends AbstractDAO<Rental> {
    public RentalDAO(Session session) {
        super(Rental.class, session);
    }

    public Rental getUnfinishedRental() {
        Session session = getCurrentSession();
        Query<Rental> query = session.createQuery("select r from Rental r where r.returnDate is null", Rental.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}