package dao;

import entity.Staff;
import org.hibernate.Session;

public class StaffDAO extends AbstractDAO<Staff> {
    public StaffDAO(Session session) {
        super(Staff.class, session);
    }
}