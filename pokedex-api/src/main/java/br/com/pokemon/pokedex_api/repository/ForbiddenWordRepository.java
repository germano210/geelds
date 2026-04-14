package br.com.pokemon.pokedex_api.repository;

import br.com.pokemon.pokedex_api.model.ForbiddenWord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ForbiddenWordRepository extends JpaRepository<ForbiddenWord, Long> {
    Optional<ForbiddenWord> findByWordIgnoreCase(String word);
}