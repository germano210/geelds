package br.com.pokemon.pokedex_api.controller;

import br.com.pokemon.pokedex_api.dto.PokemonDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap; // Necessário para o mapa de status
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {
    private final RestTemplate restTemplate = new RestTemplate();
    // Offset configurado conforme sua preferência anterior
    private final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon?limit=25&offset=75";

    @Cacheable("pokemons")
    @GetMapping
    public List<PokemonDTO> getPokemons(){
        List<PokemonDTO> pokemons = new ArrayList<>();

        try {
            JsonNode response = restTemplate.getForObject(POKEAPI_URL, JsonNode.class);
            if (response != null && response.has("results")) {
                for (JsonNode result : response.get("results")) {
                    String pokemonUrl = result.get("url").asText();
                    try {
                        JsonNode pokemonDetalhes = restTemplate.getForObject(pokemonUrl, JsonNode.class);

                        if (pokemonDetalhes != null) {
                            PokemonDTO pokemonDTO = new PokemonDTO();
                            pokemonDTO.setNumero(pokemonDetalhes.get("id").asText());
                            pokemonDTO.setNome(pokemonDetalhes.get("name").asText());

                            // Imagens (Sprites)
                            JsonNode sprites = pokemonDetalhes.get("sprites").get("versions").get("generation-iii").get("firered-leafgreen");
                            pokemonDTO.setImageURL(sprites.get("front_default").asText());
                            pokemonDTO.setImageShinyURL(sprites.get("front_shiny").asText());

                            // Tipos
                            List<String> tipos = new ArrayList<>();
                            for (JsonNode tipoInfo : pokemonDetalhes.get("types")) {
                                tipos.add(tipoInfo.get("type").get("name").asText());
                            }
                            pokemonDTO.setTipos(tipos);

                            // --- NOVA LÓGICA: Extração de Status Base ---
                            Map<String, Integer> statusMap = new HashMap<>();
                            if (pokemonDetalhes.has("stats")) {
                                for (JsonNode statNode : pokemonDetalhes.get("stats")) {
                                    String statName = statNode.get("stat").get("name").asText();
                                    int baseStat = statNode.get("base_stat").asInt();
                                    statusMap.put(statName, baseStat);
                                }
                            }
                            pokemonDTO.setStatus(statusMap);
                            // ---------------------------------------------

                            pokemons.add(pokemonDTO);
                        }
                    }catch(Exception e) {
                        System.err.println("Erro ao processar detalhes da URL: "+pokemonUrl+" ; "+e.getMessage());
                    }
                }
            }
        }catch(HttpClientErrorException e){
            System.err.println("Erro de conexão com a PokéAPI:\n"+e.getMessage());
        }

        return pokemons;
    }
}