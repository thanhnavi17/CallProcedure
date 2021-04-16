package thanh.pokeAPI.repository;

import thanh.pokeAPI.model.Pokemon;

import java.util.List;

public interface PokemonRepositoryCustom {
    List<Pokemon> getAllPokemons();
    Pokemon findPokemonById(int id);
    Pokemon createPokemon(Pokemon pokemon);
    Pokemon updatePokemon(Pokemon pokemon);
    boolean deletePokemon(int id);
    List<Pokemon> findPokemonByKeyword(String keyword);
}
