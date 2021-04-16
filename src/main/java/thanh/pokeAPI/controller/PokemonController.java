package thanh.pokeAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thanh.pokeAPI.model.Pokemon;
import thanh.pokeAPI.repository.PokemonRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/pokemon")
public class PokemonController {
    @Autowired
    PokemonRepository pokemonRepository;

    @GetMapping("")
    public ResponseEntity<List<Pokemon>> getAllPokemons(){
        Iterable<Pokemon> pokemons =pokemonRepository.getAllPokemons();

        List<Pokemon> lstPokemon = new ArrayList<>();

        pokemons.forEach(lstPokemon::add);
        return new ResponseEntity<>(lstPokemon, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> findPokemonById(@PathVariable("id") int id){
        Pokemon pokemon = (Pokemon) pokemonRepository.findPokemonById(id);
        return new ResponseEntity<>(pokemon, HttpStatus.OK);
    }

    @PostMapping("")
    public Pokemon createPokemon(@Valid @RequestBody Pokemon pokemon){

        return pokemonRepository.createPokemon(pokemon);
    }

    @PutMapping("/{id}")
    public Pokemon updatePokemon(@PathVariable("id") int id, @Valid @RequestBody Pokemon pokemon){
        Pokemon objPokemon = (Pokemon) pokemonRepository.findPokemonById(id);

        objPokemon.setPokemonName(pokemon.getPokemonName());
        objPokemon.setSpecies(pokemon.getSpecies());
        Pokemon updatePkm =pokemonRepository.updatePokemon(objPokemon);

        return updatePkm;
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deletePokemon(@PathVariable("id") int id){
        pokemonRepository.deletePokemon(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Pokemon>> findPokemonByKeyword(@PathVariable("keyword") String keyword){
        List<Pokemon> lst = pokemonRepository.findPokemonByKeyword(keyword);
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }
}
