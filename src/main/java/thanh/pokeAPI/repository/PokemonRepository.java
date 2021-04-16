package thanh.pokeAPI.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import thanh.pokeAPI.model.Pokemon;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Integer>, PokemonRepositoryCustom {

}
