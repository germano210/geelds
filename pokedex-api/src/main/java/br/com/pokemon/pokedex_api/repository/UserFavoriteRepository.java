package br.com.pokemon.pokedex_api.repository;

import br.com.pokemon.pokedex_api.model.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Long> {
    List<UserFavorite> findByUserId(Long userId);
    boolean existsByUserIdAndPokemonId(Long userId, Integer pokemonId);
}