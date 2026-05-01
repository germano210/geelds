package br.com.pokemon.pokedex_api.dto;

import br.com.pokemon.pokedex_api.model.PokemonBuild;
import java.util.ArrayList;
import java.util.List;

public class PasteDTO {
    private String pokemonName;
    private PokemonBuild build;
    private List<String> moveNames;

    public PasteDTO() {
        this.build = new PokemonBuild();
        this.moveNames = new ArrayList<>();
    }

    public String getPokemonName() { return pokemonName; }
    public void setPokemonName(String pokemonName) { this.pokemonName = pokemonName; }

    public PokemonBuild getBuild() { return build; }
    public void setBuild(PokemonBuild build) { this.build = build; }

    public List<String> getMoveNames() { return moveNames; }
    public void setMoveNames(List<String> moveNames) { this.moveNames = moveNames; }
}