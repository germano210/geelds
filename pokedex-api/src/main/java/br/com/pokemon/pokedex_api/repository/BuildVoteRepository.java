package br.com.pokemon.pokedex_api.repository;

import br.com.pokemon.pokedex_api.model.BuildVote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BuildVoteRepository extends JpaRepository<BuildVote, Long> {
    Optional<BuildVote> findByUserIdAndBuildId(Long userId, Long buildId);
}