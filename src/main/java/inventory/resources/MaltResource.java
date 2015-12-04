package inventory.resources;

import inventory.db.MaltDAO;
import inventory.model.Malt;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/yeasts")
@Produces(MediaType.APPLICATION_JSON)
public class MaltResource extends BaseResource<Malt> {

    private final MaltDAO maltDAO;

    public MaltResource(MaltDAO maltDAO) {
        super(maltDAO);
        this.maltDAO = maltDAO;
    }

    @GET
    @UnitOfWork
    @PermitAll
    public List<Malt> findAll() {
        return maltDAO.findAll();
    }

}