package dao;

import entity.Address;
import org.hibernate.Session;

public class AddressDAO extends AbstractDAO<Address> {
    public AddressDAO(Session session) {
        super(Address.class, session);
    }
}