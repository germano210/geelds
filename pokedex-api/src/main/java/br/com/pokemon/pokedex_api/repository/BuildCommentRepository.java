package br.com.pokemon.pokedex_api.repository;

import br.com.pokemon.pokedex_api.model.BuildComment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BuildCommentRepository extends JpaRepository<BuildComment, Long> {
    List<BuildComment> findByBuildIdAndParentCommentIsNull(Long buildId);
}