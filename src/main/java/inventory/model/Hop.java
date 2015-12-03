package inventory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Getter
@NamedQueries({
        @NamedQuery(name = "model.Hop.findAll",
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

    public Hop(String name, Double alpha, HopType hopType, int amount) {
        this.name = name;
        this.alpha = alpha;
        this.hopType = hopType;
        this.amount = amount;
    }

    public void setId(long id) {
        this.id = id;
    }
}
