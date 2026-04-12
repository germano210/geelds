package br.com.pokemon.pokedex_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "build_moves")
public class BuildMove {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "build_id", nullable = false)
    private PokemonBuild build;

    @Column(name = "move_name", nullable = false, length = 100)
    private String moveName;

    public BuildMove() {}

    public BuildMove(String moveName) {
        this.moveName = moveName;
    }

    public String getMoveName() {
        return moveName;
    }

    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }

    public PokemonBuild getBuild() {
        return build;
    }

    public void setBuild(PokemonBuild build) {
        this.build = build;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}