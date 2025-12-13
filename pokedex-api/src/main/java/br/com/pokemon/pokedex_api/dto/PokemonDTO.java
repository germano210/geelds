package br.com.pokemon.pokedex_api.dto;

import java.util.List;
import java.util.Map;

/**
 * Objeto de Transferência de Dados (DTO) para os Pokémons.
 * Atualizado para incluir o mapa de Status Base (HP, Attack, etc).
 */
public class PokemonDTO {
    private String numero;
    private String nome;
    private String imageURL;
    private String imageShinyURL;
    private List<String> tipos;
    private Map<String, Integer> status; // Novo campo para os Stats

    // Getters e Setters

    public String getNumero(){return numero;}
    public void setNumero(String numero){this.numero=numero;}

    public String getNome() { return nome; }
    public void setNome(String nome){ this.nome=nome; }

    public String getImageURL(){ return imageURL; }
    public void setImageURL(String imageURL){ this.imageURL=imageURL; }

    public List<String> getTipos() { return tipos; }
    public void setTipos(List<String> tipos){ this.tipos=tipos; }

    public String getImageShinyURL() { return imageShinyURL; }
    public void setImageShinyURL(String imageShinyURL) { this.imageShinyURL = imageShinyURL; }

    public Map<String, Integer> getStatus() { return status; }
    public void setStatus(Map<String, Integer> status) { this.status = status; }
}