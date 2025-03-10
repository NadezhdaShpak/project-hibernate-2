package dao;

import entity.Film;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class FilmDAO extends AbstractDAO<Film> {
    public FilmDAO(Session session) {
        super(Film.class, session);
    }

    public Film getAvailableFilm() {
        Session session = getCurrentSession();

        Query<Film> query = session.createQuery(
                "SELECT DISTINCT f " +
                "FROM Film f " +
                "JOIN Inventory i ON f.id = i.film.id " +
                "LEFT JOIN Rental r ON i.id = r.inventory.id AND r.returnDate IS NULL " +
                "WHERE r.id IS NULL", Film.class);
        query.setMaxResults(1);
        return query.uniqueResult();
    }
}