package br.com.pokemon.pokedex_api.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "pokemon_moderators")
public class PokemonModerator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "pokemon_id", nullable = false)
    private Integer pokemonId;

    @Column(name = "assigned_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime assignedAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Integer getPokemonId() { return pokemonId; }
    public void setPokemonId(Integer pokemonId) { this.pokemonId = pokemonId; }

    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }
}