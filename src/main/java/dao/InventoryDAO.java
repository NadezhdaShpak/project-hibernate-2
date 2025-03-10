package dao;

import entity.Inventory;
import org.hibernate.Session;

public class InventoryDAO extends AbstractDAO<Inventory> {
    public InventoryDAO(Session session) {
        super(Inventory.class, session);
    }
}