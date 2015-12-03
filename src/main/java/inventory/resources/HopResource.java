package inventory.resources;

import com.codahale.metrics.annotation.Timed;
import inventory.db.HopDAO;
import inventory.model.Hop;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/hops")
@Produces(MediaType.APPLICATION_JSON)
public class HopResource extends BaseResource<Hop> {

    private final HopDAO hopDAO;

    public HopResource(HopDAO hopDAO) {
        super(hopDAO);
        this.hopDAO = hopDAO;
    }

    @GET
    @UnitOfWork
    @Timed
    @PermitAll
    public List<Hop> findAll() {
        return hopDAO.findAll();
    }

}
