package dao;

import entity.Actor;
import org.hibernate.Session;

public class ActorDAO extends AbstractDAO<Actor> {
    public ActorDAO(Session session) {
        super(Actor.class, session);
    }
}
