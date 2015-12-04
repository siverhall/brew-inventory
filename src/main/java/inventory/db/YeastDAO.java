package inventory.db;

import inventory.model.Yeast;
import org.hibernate.SessionFactory;

import java.util.List;

public class YeastDAO extends BaseDAO<Yeast> {

    public YeastDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Yeast> findAll() {
        return list(namedQuery("model.Yeast.findAll"));
    }
}
