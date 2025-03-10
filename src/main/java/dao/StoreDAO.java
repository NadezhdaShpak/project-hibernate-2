package dao;

import entity.Store;
import org.hibernate.Session;

public class StoreDAO extends AbstractDAO<Store> {
    public StoreDAO(Session session) {
        super(Store.class, session);
    }
}