package inventory.resources;

import inventory.db.IngredientDAO;
import inventory.model.Malt;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/malts")
@Produces(MediaType.APPLICATION_JSON)
public class MaltResource extends BaseResource<Malt> {

    public MaltResource(IngredientDAO<Malt> maltDAO) {
        super(maltDAO);
    }

}
