package inventory.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class JSONTest<T> {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture(getFixture()), getClazz()));

        assertThat(MAPPER.writeValueAsString(createObject())).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        assertThat(MAPPER.readValue(fixture(getFixture()), getClazz()))
                .isEqualTo(createObject());
    }

    protected abstract Class<T> getClazz();

    protected abstract String getFixture();

    protected abstract T createObject();

}
