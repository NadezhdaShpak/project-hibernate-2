package dao;

import entity.FilmText;
import org.hibernate.Session;

public class FilmTextDAO extends AbstractDAO<FilmText> {
    public FilmTextDAO(Session session) {
        super(FilmText.class, session);
    }
}