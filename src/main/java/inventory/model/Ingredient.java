package inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Getter
@NamedQueries({
        @NamedQuery(name = "api.Ingredient.findAll",
        query = "SELECT i FROM Ingredient i")
})
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private IngredientType ingredientType;

    public void setId(long id) {
        this.id = id;
    }

    public Ingredient() {
        //Jackson specific
    }

    public Ingredient(String name, IngredientType ingredientType) {
        this.name = name;
        this.ingredientType = ingredientType;
    }

    public enum IngredientType {
        MALT, HOP, YEAST, OTHER
    }


}
