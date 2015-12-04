package inventory.db;

import inventory.model.Malt;
import org.hibernate.SessionFactory;

import java.util.List;

public class MaltDAO extends BaseDAO<Malt> {

    public MaltDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Malt> findAll() {
        return list(namedQuery("model.Malt.findAll"));
    }
}
