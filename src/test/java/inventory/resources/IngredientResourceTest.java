package inventory.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import inventory.db.IngredientDao;
import inventory.model.Ingredient;
import io.dropwizard.jackson.Jackson;
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
public class IngredientResourceTest {

    private static final IngredientDao dao = mock(IngredientDao.class);
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new IngredientResource(dao))
            .build();

    private Optional<Ingredient> optional;

    @Before
    public void setUp() throws Exception {
        Ingredient ingredient = new Ingredient("Amarillo", Ingredient.IngredientType.HOP);
        ingredient.setId(1);
        optional = Optional.of(ingredient);

        when(dao.findById(anyLong())).thenReturn(optional);

    }

    @After
    public void tearDown() throws Exception {
        reset(dao);
    }

    @Test
    public void testFindIngredient() throws Exception {
        assertThat(resources.client().target("/ingredients/1").request().get(Ingredient.class))
                .isEqualTo(optional.get());
        verify(dao).findById(1);
    }

    @Test
    public void testFindAll() throws Exception {
        ImmutableList<Ingredient> ingredients = ImmutableList.of(optional.get());
        when(dao.findAll()).thenReturn(ingredients);

        List<Ingredient> response = resources.client().target("/ingredients")
                .request().get(new GenericType<List<Ingredient>>() {});

        verify(dao).findAll();
        assertThat(response).containsAll(ingredients);
    }
}
