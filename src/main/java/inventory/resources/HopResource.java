package inventory.resources;

import inventory.db.IngredientDAO;
import inventory.model.Hop;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hops")
@Produces(MediaType.APPLICATION_JSON)
public class HopResource extends BaseResource<Hop> {

    public HopResource(IngredientDAO<Hop> hopDAO) {
        super(hopDAO);
    }

}
