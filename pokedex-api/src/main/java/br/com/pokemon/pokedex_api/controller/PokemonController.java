package br.com.pokemon.pokedex_api.controller;

import br.com.pokemon.pokedex_api.dto.PokemonDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon?limit=80&offset=30";

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
                        String pokemonSpeciesUrl = pokemonDetalhes.get("species").get("url").asText();
                        JsonNode pokemonSpecies = restTemplate.getForObject(pokemonSpeciesUrl, JsonNode.class);
                        if (pokemonDetalhes != null) {
                            PokemonDTO pokemonDTO = new PokemonDTO();
                            pokemonDTO.setNumero(pokemonDetalhes.get("id").asText());
                            pokemonDTO.setNome(pokemonDetalhes.get("name").asText());
                            pokemonDTO.setImageURL(pokemonDetalhes.get("sprites").get("versions").get("generation-iii").get("firered-leafgreen").get("front_default").asText());
                            List<String> tipos = new ArrayList<>();
                            for (JsonNode tipoInfo : pokemonDetalhes.get("types")) {
                                tipos.add(tipoInfo.get("type").get("name").asText());
                            }
                            pokemonDTO.setTipos(tipos);
                            pokemons.add(pokemonDTO);
                        }
                    }catch(HttpClientErrorException e) {
                        System.err.println("Não foi possivel encontrar os detalhes nesta URL: "+pokemonUrl+" ; "+e.getMessage());
                    }
                }
            }
        }catch(HttpClientErrorException e){
            System.err.println("Pokemon(s) não encontrado(s)\n"+e.getMessage());
        }
        System.out.println(pokemons);
        return pokemons;
    }
}
