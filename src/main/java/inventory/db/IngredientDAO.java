package inventory.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class IngredientDAO<T> extends AbstractDAO<T> {

    private final Class<T> c;

    public IngredientDAO(SessionFactory sessionFactory, Class<T> c) {
        super(sessionFactory);
        this.c = c;
    }

    public Optional<T> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    public List<T> findAll() {
        return list(namedQuery(c.getName()+".findAll"));
    }

    public T save(T entity) {
        return persist(entity);
    }

    public void delete(T entity) {
        currentSession().delete(entity);
    }
}
