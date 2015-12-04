package inventory.resources;

import com.codahale.metrics.annotation.Timed;
import inventory.db.BaseDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import java.util.Optional;

public abstract class BaseResource<T> {

    private BaseDAO<T> dao;

    public BaseResource(BaseDAO<T> dao) {
        this.dao = dao;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    @PermitAll
    public T getOne(@PathParam("id") long id) {
        return findSafely(id);
    }

    @POST
    @UnitOfWork
    @PermitAll
    public void save(T entity) {
        dao.save(entity);
    }

    @DELETE
    @UnitOfWork
    @Path("/{id}")
    @PermitAll
    public void delete(@PathParam("id") long id) {
        T entity = findSafely(id);
        dao.delete(entity);
    }

    private T findSafely(long id) {
        final Optional<T> optional = dao.findById(id);
        if (!optional.isPresent()) {
            throw new NotFoundException("Not found.");
        }
        return optional.get();
    }
}
