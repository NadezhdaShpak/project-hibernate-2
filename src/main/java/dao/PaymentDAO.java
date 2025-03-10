package dao;

import entity.Payment;
import org.hibernate.Session;

public class PaymentDAO extends AbstractDAO<Payment> {
    public PaymentDAO(Session session) {
        super(Payment.class, session);
    }
}