package br.com.pokemon.pokedex_api.dto;

import java.util.List;

public class PokemonDTO {
    private String numero;
    private String nome;
    private String imageURL;
    private String imageShinyURL;
    private List<String> tipos;

    //getters e setters

    public String getNumero(){return numero;}

    public void setNumero(String numero){this.numero=numero;}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public String getImageURL(){
        return imageURL;
    }

    public void setImageURL(String imageURL){
        this.imageURL=imageURL;
    }

    public List<String> getTipos() {
        return tipos;
    }

    public void setTipos(List<String> tipos){
        this.tipos=tipos;
    }

    public String getImageShinyURL() { return imageShinyURL; }

    public void setImageShinyURL(String imageShinyURL) { this.imageShinyURL = imageShinyURL; }
}
