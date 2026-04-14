package br.com.pokemon.pokedex_api.service;

import br.com.pokemon.pokedex_api.model.ForbiddenWord;
import br.com.pokemon.pokedex_api.repository.ForbiddenWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModerationService {

    @Autowired
    private ForbiddenWordRepository forbiddenWordRepository;

    public boolean containsBadWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }

        List<ForbiddenWord> badWords = forbiddenWordRepository.findAll();
        String textLower = text.toLowerCase();

        for (ForbiddenWord badWord : badWords) {
            String regex = "\\b" + badWord.getWord().toLowerCase() + "\\b";
            if (textLower.matches(".*" + regex + ".*")) {
                return true;
            }
        }
        return false;
    }
}