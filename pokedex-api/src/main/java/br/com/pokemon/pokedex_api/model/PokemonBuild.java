package br.com.pokemon.pokedex_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pokemon_builds")
public class PokemonBuild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "pokemon_id", nullable = false)
    private Integer pokemonId;

    @Column(name = "build_name", nullable = false, length = 100)
    private String buildName;

    @Column(length = 100)
    private String item;

    @Column(length = 100)
    private String ability;

    @Column(length = 50)
    private String nature;

    @Column(columnDefinition = "integer default 100")
    private Integer level = 100;

    @Column(name = "tera_type", length = 50)
    private String teraType;

    @Column(length = 1)
    private String gender;

    @Column(columnDefinition = "integer default 255")
    private Integer happiness = 255;

    @Column(name = "is_shiny", columnDefinition = "boolean default false")
    private Boolean isShiny = false;

    @Column(name = "ev_hp", columnDefinition = "integer default 0")
    private Integer evHp = 0;
    @Column(name = "ev_atk", columnDefinition = "integer default 0")
    private Integer evAtk = 0;
    @Column(name = "ev_def", columnDefinition = "integer default 0")
    private Integer evDef = 0;
    @Column(name = "ev_spa", columnDefinition = "integer default 0")
    private Integer evSpa = 0;
    @Column(name = "ev_spd", columnDefinition = "integer default 0")
    private Integer evSpd = 0;
    @Column(name = "ev_spe", columnDefinition = "integer default 0")
    private Integer evSpe = 0;

    @Column(name = "iv_hp", columnDefinition = "integer default 31")
    private Integer ivHp = 31;
    @Column(name = "iv_atk", columnDefinition = "integer default 31")
    private Integer ivAtk = 31;
    @Column(name = "iv_def", columnDefinition = "integer default 31")
    private Integer ivDef = 31;
    @Column(name = "iv_spa", columnDefinition = "integer default 31")
    private Integer ivSpa = 31;
    @Column(name = "iv_spd", columnDefinition = "integer default 31")
    private Integer ivSpd = 31;
    @Column(name = "iv_spe", columnDefinition = "integer default 31")
    private Integer ivSpe = 31;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "build", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BuildMove> moves = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public void addMove(BuildMove move) {
        moves.add(move);
        move.setBuild(this);
    }

    public void removeMove(BuildMove move) {
        moves.remove(move);
        move.setBuild(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(Integer pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTeraType() {
        return teraType;
    }

    public void setTeraType(String teraType) {
        this.teraType = teraType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getHappiness() {
        return happiness;
    }

    public void setHappiness(Integer happiness) {
        this.happiness = happiness;
    }

    public Boolean getShiny() {
        return isShiny;
    }

    public void setShiny(Boolean shiny) {
        isShiny = shiny;
    }

    public Integer getEvHp() {
        return evHp;
    }

    public void setEvHp(Integer evHp) {
        this.evHp = evHp;
    }

    public Integer getEvAtk() {
        return evAtk;
    }

    public void setEvAtk(Integer evAtk) {
        this.evAtk = evAtk;
    }

    public Integer getEvDef() {
        return evDef;
    }

    public void setEvDef(Integer evDef) {
        this.evDef = evDef;
    }

    public Integer getEvSpa() {
        return evSpa;
    }

    public void setEvSpa(Integer evSpa) {
        this.evSpa = evSpa;
    }

    public Integer getEvSpd() {
        return evSpd;
    }

    public void setEvSpd(Integer evSpd) {
        this.evSpd = evSpd;
    }

    public Integer getEvSpe() {
        return evSpe;
    }

    public void setEvSpe(Integer evSpe) {
        this.evSpe = evSpe;
    }

    public Integer getIvHp() {
        return ivHp;
    }

    public void setIvHp(Integer ivHp) {
        this.ivHp = ivHp;
    }

    public Integer getIvAtk() {
        return ivAtk;
    }

    public void setIvAtk(Integer ivAtk) {
        this.ivAtk = ivAtk;
    }

    public Integer getIvDef() {
        return ivDef;
    }

    public void setIvDef(Integer ivDef) {
        this.ivDef = ivDef;
    }

    public Integer getIvSpa() {
        return ivSpa;
    }

    public void setIvSpa(Integer ivSpa) {
        this.ivSpa = ivSpa;
    }

    public Integer getIvSpd() {
        return ivSpd;
    }

    public void setIvSpd(Integer ivSpd) {
        this.ivSpd = ivSpd;
    }

    public Integer getIvSpe() {
        return ivSpe;
    }

    public void setIvSpe(Integer ivSpe) {
        this.ivSpe = ivSpe;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<BuildMove> getMoves() {
        return moves;
    }

    public void setMoves(List<BuildMove> moves) {
        this.moves = moves;
    }
}