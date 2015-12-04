package inventory.resources;

import com.codahale.metrics.annotation.Timed;
import inventory.db.YeastDAO;
import inventory.model.Yeast;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/yeasts")
@Produces(MediaType.APPLICATION_JSON)
public class YeastResource extends BaseResource<Yeast> {

    private final YeastDAO yeastDAO;

    public YeastResource(YeastDAO yeastDAO) {
        super(yeastDAO);
        this.yeastDAO = yeastDAO;
    }

    @GET
    @UnitOfWork
    @PermitAll
    public List<Yeast> findAll() {
        return yeastDAO.findAll();
    }

}
