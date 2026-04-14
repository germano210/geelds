package br.com.pokemon.pokedex_api.service;

import br.com.pokemon.pokedex_api.model.BuildComment;
import br.com.pokemon.pokedex_api.model.BuildVote;
import br.com.pokemon.pokedex_api.repository.BuildCommentRepository;
import br.com.pokemon.pokedex_api.repository.BuildVoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocialService {

    @Autowired
    private BuildVoteRepository voteRepository;

    @Autowired
    private BuildCommentRepository commentRepository;

    @Autowired
    private ModerationService moderationService;

    public BuildComment adicionarComentario(BuildComment comentario) {
        if (moderationService.containsBadWords(comentario.getContent())) {
            throw new IllegalArgumentException("Comentário bloqueado pelo filtro de toxicidade.");
        }
        return commentRepository.save(comentario);
    }

    public void registrarVoto(BuildVote novoVoto) {
        // Verifica se o usuário já votou nesta build antes
        Optional<BuildVote> votoExistente = voteRepository.findByUserIdAndBuildId(
                novoVoto.getUser().getId(), novoVoto.getBuild().getId());

        if (votoExistente.isPresent()) {
            // Se já votou, apenas atualiza se foi upvote ou downvote
            BuildVote voto = votoExistente.get();
            voto.setVoteType(novoVoto.getVoteType());
            voteRepository.save(voto);
        } else {
            // Se é voto novo, salva no banco
            voteRepository.save(novoVoto);
        }
    }
}