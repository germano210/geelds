package br.com.pokemon.pokedex_api.service;

import br.com.pokemon.pokedex_api.model.User;
import br.com.pokemon.pokedex_api.model.UserSeenPokemon;
import br.com.pokemon.pokedex_api.repository.UserRepository;
import br.com.pokemon.pokedex_api.repository.UserSeenPokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProgressService {

    @Autowired
    private UserSeenPokemonRepository seenRepository;

    @Autowired
    private UserRepository userRepository;

    public void registrarPokemonVisto(User user, Integer pokemonId) {
        boolean jaViu = seenRepository.existsByUserIdAndPokemonId(user.getId(), pokemonId);

        if (!jaViu) {
            UserSeenPokemon visto = new UserSeenPokemon();
            visto.setUser(user);
            visto.setPokemonId(pokemonId);
            seenRepository.save(visto);
        }
    }

    public User atualizarPreferencias(Long userId, boolean darkMode, boolean realMode) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setDarkMode(darkMode);
        user.setRealMode(realMode);
        return userRepository.save(user);
    }
}