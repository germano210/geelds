package br.com.pokemon.pokedex_api.repository;

import br.com.pokemon.pokedex_api.model.UserSeenPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserSeenPokemonRepository extends JpaRepository<UserSeenPokemon, Long> {
    List<UserSeenPokemon> findByUserId(Long userId);
    boolean existsByUserIdAndPokemonId(Long userId, Integer pokemonId);
}