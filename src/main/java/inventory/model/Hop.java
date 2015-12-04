package inventory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "inventory.model.Hop.findAll",
                query = "SELECT h FROM Hop h")
})
public class Hop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private Double alpha;

    @JsonProperty
    @Enumerated(EnumType.STRING)
    private HopType hopType;

    @JsonProperty
    private int amount;

    public enum HopType {
        PELLET, WHOLE_LEAF
    }

    public Hop() {}

}
