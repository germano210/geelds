package br.com.pokemon.pokedex_api.service;

import br.com.pokemon.pokedex_api.model.BuildMove;
import br.com.pokemon.pokedex_api.model.PokemonBuild;
import br.com.pokemon.pokedex_api.repository.BuildMoveRepository;
import br.com.pokemon.pokedex_api.repository.PokemonBuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PokemonBuildService {

    @Autowired
    private PokemonBuildRepository buildRepository;

    @Autowired
    private BuildMoveRepository moveRepository;

    @Autowired
    private ModerationService moderationService;

    @Transactional
    public PokemonBuild criarBuild(PokemonBuild novaBuild, List<String> moveNames) {
        // Regra 1: Validação de Palavrões no Guia e no Nome
        if (moderationService.containsBadWords(novaBuild.getGuideText()) ||
                moderationService.containsBadWords(novaBuild.getBuildName())) {
            throw new IllegalArgumentException("O texto contém palavras impróprias.");
        }

        // Regra 2: Salva a Build primeiro para gerar o ID
        PokemonBuild buildSalva = buildRepository.save(novaBuild);

        // Regra 3: Associa e salva até 4 golpes
        if (moveNames != null && !moveNames.isEmpty()) {
            for (int i = 0; i < Math.min(moveNames.size(), 4); i++) {
                BuildMove move = new BuildMove();
                move.setBuild(buildSalva);
                move.setMoveName(moveNames.get(i));
                moveRepository.save(move);
            }
        }

        return buildSalva;
    }

    public List<PokemonBuild> buscarBuildsPorPokemon(Integer pokemonId) {
        return buildRepository.findByPokemonId(pokemonId);
    }
}