package br.com.pokemon.pokedex_api.repository;

import br.com.pokemon.pokedex_api.model.PokemonModerator;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PokemonModeratorRepository extends JpaRepository<PokemonModerator, Long> {
    List<PokemonModerator> findByPokemonId(Integer pokemonId);
    boolean existsByUserIdAndPokemonId(Long userId, Integer pokemonId);
}