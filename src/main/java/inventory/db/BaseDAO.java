package inventory.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Optional;

public abstract class BaseDAO<T> extends AbstractDAO<T> {

    public BaseDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<T> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    public T save(T entity) {
        return persist(entity);
    }

    public void delete(T entity) {
        currentSession().delete(entity);
    }
}
