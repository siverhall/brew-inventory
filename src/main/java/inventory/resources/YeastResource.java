package inventory.resources;

import inventory.db.IngredientDAO;
import inventory.model.Yeast;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/yeasts")
@Produces(MediaType.APPLICATION_JSON)
public class YeastResource extends BaseResource<Yeast> {

    public YeastResource(IngredientDAO<Yeast> yeastDAO) {
        super(yeastDAO);
    }

}
