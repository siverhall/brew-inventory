package inventory.resources;

import com.google.common.collect.ImmutableList;
import inventory.db.HopDAO;
import inventory.model.Hop;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.GenericType;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HopResourceTest {

    private static final HopDAO dao = mock(HopDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new HopResource(dao))
            .build();

    private Optional<Hop> optional;

    @Before
    public void setUp() throws Exception {
        Hop hop = createHop();
        hop.setId(1);
        optional = Optional.of(hop);

        when(dao.findById(anyLong())).thenReturn(optional);

    }

    @After
    public void tearDown() throws Exception {
        reset(dao);
    }

    @Test
    public void testFindHop() throws Exception {
        assertThat(resources.client().target("/hops/1").request().get(Hop.class))
                .isEqualTo(optional.get());
        verify(dao).findById(1);
    }

    @Test
    public void testFindAll() throws Exception {
        ImmutableList<Hop> hops = ImmutableList.of(optional.get());
        when(dao.findAll()).thenReturn(hops);

        List<Hop> response = resources.client().target("/hops")
                .request().get(new GenericType<List<Hop>>() {});

        verify(dao).findAll();
        assertThat(response).containsAll(hops);
    }

    private Hop createHop() {
        Hop hop = new Hop();
        hop.setId(1);
        hop.setAlpha(12.0);
        hop.setName("Amarillo");
        hop.setAmount(30);
        hop.setHopType(Hop.HopType.PELLET);
        return hop;
    }
}
