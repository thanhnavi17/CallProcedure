package thanh.pokeAPI.model;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="pokemon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name="getAllPokemons", procedureName = "get_all_pokemon", resultClasses =
                Pokemon.class),
        @NamedStoredProcedureQuery(name= "createPokemon", procedureName = "createpokemon", resultClasses =
                Pokemon.class),
        @NamedStoredProcedureQuery(name = "updatePokemon", procedureName = "update_pokemon", resultClasses =
                Pokemon.class),
        @NamedStoredProcedureQuery(name = "findPokemonById", procedureName = "find_pokemon_by_id", resultClasses =
                Pokemon.class),
        @NamedStoredProcedureQuery(name = "deletePokemon", procedureName = "delete_pokemon", resultClasses =
                Pokemon.class),
        @NamedStoredProcedureQuery(name = "searchPokemon", procedureName = "search_pokemon",resultClasses = Pokemon.class)
})
public class Pokemon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    private String pokemonName;

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    @Column
    private String species;

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
