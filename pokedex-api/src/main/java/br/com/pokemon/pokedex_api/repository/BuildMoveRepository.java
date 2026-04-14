package br.com.pokemon.pokedex_api.repository;

import br.com.pokemon.pokedex_api.model.BuildMove;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BuildMoveRepository extends JpaRepository<BuildMove, Long> {
    List<BuildMove> findByBuildId(Long buildId);
}