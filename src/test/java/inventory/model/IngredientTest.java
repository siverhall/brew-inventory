package inventory.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class IngredientTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/ingredient.json"), Ingredient.class));

        assertThat(MAPPER.writeValueAsString(getIngredient())).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        assertThat(MAPPER.readValue(fixture("fixtures/ingredient.json"), Ingredient.class))
                .isEqualTo(getIngredient());
    }

    private Ingredient getIngredient() {
        Ingredient ingredient = new Ingredient("Amarillo", Ingredient.IngredientType.HOP);
        ingredient.setId(1);
        return ingredient;
    }
}
