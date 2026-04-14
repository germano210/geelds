package br.com.pokemon.pokedex_api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokeApiService {

    public Integer getPokemonIdByName(String name) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://pokeapi.co/api/v2/pokemon/" + name.toLowerCase();

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            return root.path("id").asInt();

        } catch (Exception e) {
            System.out.println("Erro ao buscar Pokémon na PokeAPI: " + e.getMessage());
            return null;
        }
    }
}