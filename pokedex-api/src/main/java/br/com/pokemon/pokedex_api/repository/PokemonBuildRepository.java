package br.com.pokemon.pokedex_api.repository;

import br.com.pokemon.pokedex_api.model.PokemonBuild;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PokemonBuildRepository extends JpaRepository<PokemonBuild, Long> {

    List<PokemonBuild> findByPokemonId(Integer pokemonId);

    List<PokemonBuild> findByUserId(Long userId);

    List<PokemonBuild> findByPokemonIdAndStatus(Integer pokemonId, String status);

    List<PokemonBuild> findAllByStatusOrderByVoteCountDesc(String status);
}