package inventory.resources;

import com.codahale.metrics.annotation.Timed;
import inventory.model.Ingredient;
import inventory.db.IngredientDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/ingredients")
@Produces(MediaType.APPLICATION_JSON)
public class IngredientResource {

    private final IngredientDao ingredientDao;

    public IngredientResource(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    @Timed
    public Ingredient getIngredient(@PathParam("id") long id) {
        return findSafely(id);
    }

    @GET
    @UnitOfWork
    @Timed
    public List<Ingredient> findAll() {
        return ingredientDao.findAll();
    }

    @POST
    @UnitOfWork
    @Timed
    public void save(Ingredient ingredient) {
            ingredientDao.save(ingredient);
    }

    @DELETE
    @UnitOfWork
    @Path("/{id}")
    public void delete(@PathParam("id") long id) {
        Ingredient ingredient = findSafely(id);
        ingredientDao.delete(ingredient);
    }

    private Ingredient findSafely(long id) {
        final Optional<Ingredient> ingredient = ingredientDao.findById(id);
        if (!ingredient.isPresent()) {
            throw new NotFoundException("No such ingredient.");
        }
        return ingredient.get();
    }
}
