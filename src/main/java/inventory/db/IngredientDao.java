package inventory.db;

import inventory.model.Ingredient;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class IngredientDao extends AbstractDAO<Ingredient> {

    public IngredientDao(SessionFactory factory) {
        super(factory);
    }

    public Optional<Ingredient> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    public List<Ingredient> findAll() {
        return list(namedQuery("api.Ingredient.findAll"));
    }

    public Ingredient save(Ingredient ingredient) {
        return persist(ingredient);
    }

    public void delete(Ingredient ingredient) {
        currentSession().delete(ingredient);
    }
}
