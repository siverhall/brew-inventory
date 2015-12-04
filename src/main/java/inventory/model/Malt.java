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
        @NamedQuery(name = "inventory.model.Malt.findAll",
                query = "SELECT m FROM Malt m")
})
public class Malt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private int amount;


    public Malt() {}

}
