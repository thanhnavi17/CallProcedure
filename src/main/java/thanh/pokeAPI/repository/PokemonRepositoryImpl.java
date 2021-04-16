package thanh.pokeAPI.repository;

import org.springframework.stereotype.Service;
import thanh.pokeAPI.model.Pokemon;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PokemonRepositoryImpl implements PokemonRepositoryCustom{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Pokemon> getAllPokemons() {
        StoredProcedureQuery query =
                em.createNamedStoredProcedureQuery("getAllPokemons").registerStoredProcedureParameter(1, Class.class,
                        ParameterMode.REF_CURSOR);
        query.execute();
        List<Pokemon> lstPokemon = query.getResultList();
        return lstPokemon;
    }

    @Override
    public Pokemon findPokemonById(int id) {
        StoredProcedureQuery query =
                em.createStoredProcedureQuery("find_pokemon_by_id").registerStoredProcedureParameter(1,Integer.class,
                        ParameterMode.IN).registerStoredProcedureParameter(2, Class.class, ParameterMode.REF_CURSOR).setParameter(1,id);
        query.execute();
        List<Pokemon> lst = query.getResultList();
        Pokemon objPokemon = new Pokemon();
        Iterator itr = lst.iterator();
        while (itr.hasNext()){
            Object[] obj = (Object[]) itr.next();
            objPokemon.setId(Integer.parseInt(String.valueOf(obj[0])));
            objPokemon.setPokemonName(String.valueOf(obj[1]));
            objPokemon.setSpecies(String.valueOf(obj[2]));
        }
        return objPokemon;
    }

    @Override
    public Pokemon createPokemon(Pokemon pokemon) {
        StoredProcedureQuery query =
                em.createStoredProcedureQuery("createpokemon").registerStoredProcedureParameter(1,Integer.class,
                        ParameterMode.IN).registerStoredProcedureParameter(2,String.class,ParameterMode.IN).registerStoredProcedureParameter(3,String.class,ParameterMode.IN).setParameter(1,pokemon.getId()).setParameter(2,pokemon.getPokemonName()).setParameter(3,pokemon.getSpecies());
        query.execute();
        return pokemon;
    }

    @Override
    public Pokemon updatePokemon(Pokemon pokemon) {
        StoredProcedureQuery query =
                em.createStoredProcedureQuery("update_pokemon").registerStoredProcedureParameter(1,Integer.class,
                        ParameterMode.IN).registerStoredProcedureParameter(2,String.class,ParameterMode.IN).registerStoredProcedureParameter(3,String.class,ParameterMode.IN).setParameter(1,pokemon.getId()).setParameter(2,pokemon.getPokemonName()).setParameter(3,pokemon.getSpecies());
        query.execute();
        return pokemon;
    }

    @Override
    public boolean deletePokemon(int id) {
        StoredProcedureQuery query =
                em.createStoredProcedureQuery("delete_pokemon").registerStoredProcedureParameter(1,Integer.class,
                        ParameterMode.IN).setParameter(1,id);
        query.execute();
        return true;
    }

    @Override
    public List<Pokemon> findPokemonByKeyword(String keyword) {
        StoredProcedureQuery query =
                em.createNamedStoredProcedureQuery("searchPokemon").registerStoredProcedureParameter(1,String.class,
                        ParameterMode.IN).registerStoredProcedureParameter(2, Class.class, ParameterMode.REF_CURSOR).setParameter(1,"%"+keyword+"%");
        query.execute();
        List<Pokemon> lst = query.getResultList();

        return lst;
    }

}
