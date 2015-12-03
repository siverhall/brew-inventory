package inventory.db;

import inventory.model.Hop;
import org.hibernate.SessionFactory;

import java.util.List;

public class HopDAO extends BaseDAO<Hop> {

    public HopDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Hop> findAll() {
        return list(namedQuery("model.Hop.findAll"));
    }
}
