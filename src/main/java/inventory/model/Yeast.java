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
        @NamedQuery(name = "model.Yeast.findAll",
                query = "SELECT y FROM Yeast y")
})
public class Yeast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty
    private String name;

    @JsonProperty
    @Enumerated(EnumType.STRING)
    private YeastType yeastType;

    @JsonProperty
    private int amount;

    public enum YeastType {
        DRY, LIQUID
    }

    public Yeast() {}

}
