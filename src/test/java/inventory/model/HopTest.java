package inventory.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Ignore;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class HopTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/hop.json"), Hop.class));

        assertThat(MAPPER.writeValueAsString(getHop())).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        assertThat(MAPPER.readValue(fixture("fixtures/hop.json"), Hop.class))
                .isEqualTo(getHop());
    }

    private Hop getHop() {
        Hop hop = new Hop("Amarillo", 12.0, Hop.HopType.PELLET, 200);
        hop.setId(1);
        return hop;
    }
}
