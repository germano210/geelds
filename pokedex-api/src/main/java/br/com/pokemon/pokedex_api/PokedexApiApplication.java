package br.com.pokemon.pokedex_api;

import br.com.pokemon.pokedex_api.dto.PasteDTO;
import br.com.pokemon.pokedex_api.model.PokemonBuild;
import br.com.pokemon.pokedex_api.service.PasteService;
import br.com.pokemon.pokedex_api.service.PokeApiService;
import br.com.pokemon.pokedex_api.service.PokemonBuildService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@EnableCaching
@SpringBootApplication
public class PokedexApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokedexApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner testarLeitorSmogon(
            PokemonBuildService buildService,
            PasteService smogonParser,
            PokeApiService pokeApiService) { // <-- INJETAMOS O NOVO SERVIÇO AQUI

        return args -> {
            System.out.println("=== INICIANDO TESTE DO LEITOR SMOGON ===");

            String smogonText = "Cloyster @ White Herb\n" +
                    "Ability: Skill Link\n" +
                    "EVs: 252 Atk / 4 SpA / 252 Spe\n" +
                    "Jolly Nature\n" +
                    "- Shell Smash\n" +
                    "- Icicle Spear\n" +
                    "- Rock Blast\n" +
                    "- Hydro Pump" ;

            try {
                PasteDTO resultado = smogonParser.parseSmogonText(smogonText);
                System.out.println("Pokémon lido: " + resultado.getPokemonName());

                // MÁGICA ACONTECENDO: Buscamos o ID dinamicamente na PokeAPI!
                Integer pokemonId = pokeApiService.getPokemonIdByName(resultado.getPokemonName());

                if (pokemonId == null) {
                    System.out.println("Pokémon não encontrado na PokeAPI. Cancelando salvamento.");
                    return; // Interrompe o teste se o nome estiver errado
                }

                System.out.println("ID descoberto na PokeAPI: " + pokemonId);

                PokemonBuild buildParaSalvar = resultado.getBuild();
                buildParaSalvar.setPokemonId(pokemonId); // Usamos o ID dinâmico
                buildParaSalvar.setGuideText("Build importada automaticamente!");

                PokemonBuild buildSalva = buildService.criarBuild(buildParaSalvar, resultado.getMoveNames());
                System.out.println("SUCESSO! Build salva no banco com ID: " + buildSalva.getId());

            } catch (Exception e) {
                System.out.println("ERRO NO TESTE: " + e.getMessage());
                e.printStackTrace();
            }

            System.out.println("=== FIM DO TESTE ===");
        };
    }

}
