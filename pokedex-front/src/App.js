import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

/**
 * Componente principal da Pokédex.
 * Atualizado para exibir Base Stats na ordem personalizada solicitada.
 */
function App() {
  const [pokemons, setPokemons] = useState([]);
  const [selectedPokemon, setSelectedPokemon] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");

  const listaShinyFixa = ['cloyster', 'golem'];

  // Configuração da Ordem e Rótulos dos Status
  // A chave 'key' deve corresponder exatamente ao retornado pela PokéAPI.
  const statConfig = [
    { key: 'hp',              label: 'HP' },
    { key: 'speed',           label: 'SPEED' },
    { key: 'attack',          label: 'ATACK' },    // Mantendo conforme pedido (ou use ATK)
    { key: 'special-attack',  label: 'SP ATACK' },
    { key: 'defense',         label: 'DEF' },
    { key: 'special-defense', label: 'SP DEF' }
  ];

  useEffect(() => {
    const fetchPokemons = async () => {
      try {
        const response = await axios.get('http://localhost:420/api/pokemons');
        setPokemons(response.data);

        if (response.data.length > 0) {
          const firstPoke = response.data[0];
          const initialImage = resolveDisplayImage(firstPoke);
          setSelectedPokemon({ ...firstPoke, displayImage: initialImage });
        }
      } catch (error) {
        console.error("Falha ao buscar lista de pokémons:", error);
      }
    }
    fetchPokemons();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const formatId = (id) => {
    return id.toString().padStart(4, '0');
  };

  const filteredPokemons = pokemons.filter(p =>
      p.nome.toLowerCase().includes(searchTerm.toLowerCase()) ||
      p.numero.toString().includes(searchTerm)
  );

  const resolveDisplayImage = (pokemon) => {
    return listaShinyFixa.includes(pokemon.nome.toLowerCase())
        ? pokemon.imageShinyURL
        : pokemon.imageURL;
  };

  const handleSelectPokemon = (pokemon) => {
    const imageToUse = resolveDisplayImage(pokemon);
    setSelectedPokemon({ ...pokemon, displayImage: imageToUse });
  };

  return (
      <div className="pokedex-frame">
        <div className="header-bar">
          <div style={{display: 'flex', gap: '10px', alignItems: 'center'}}>
            <span>🌐 National</span>
          </div>
          <div className="header-stats">
            Caught: {pokemons.length}
          </div>
        </div>

        <div className="main-screen">
          <div className="left-pane">
            <div className="pokedex-grid">
              {filteredPokemons.map((pokemon, index) => {
                const isSelected = selectedPokemon && selectedPokemon.numero === pokemon.numero;
                const gridImage = resolveDisplayImage(pokemon);

                return (
                    <div
                        key={index}
                        className={`pokemon-slot ${isSelected ? 'selected' : ''}`}
                        onClick={() => handleSelectPokemon(pokemon)}
                    >
                      <span className="slot-number">{formatId(pokemon.numero)}</span>
                      <img
                          src={gridImage}
                          alt={pokemon.nome}
                          className="slot-img"
                      />
                    </div>
                );
              })}

              {[...Array(10)].map((_, i) => (
                  <div key={`empty-${i}`} className="pokemon-slot" style={{opacity: 0.5}}>
                    <span className="slot-number">????</span>
                  </div>
              ))}
            </div>
          </div>

          <div className="right-pane">
            <div className="search-container">
              <span style={{marginRight: '10px', color: '#666'}}>🔍</span>
              <input
                  type="text"
                  className="search-input"
                  placeholder="Search..."
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
              />
            </div>

            <div className="display-area">
              <div className="display-platform"></div>
              {selectedPokemon ? (
                  <img
                      src={selectedPokemon.displayImage}
                      alt={selectedPokemon.nome}
                      className="display-img"
                  />
              ) : (
                  <div style={{fontSize: '5rem', opacity: 0.3}}>?</div>
              )}
            </div>

            <div className="info-box">
              {selectedPokemon ? (
                  <>
                    <h2 className="info-title">
                      {formatId(selectedPokemon.numero)} {selectedPokemon.nome}
                    </h2>
                    <div style={{marginTop: '10px', marginBottom: '15px'}}>
                      {selectedPokemon.tipos.map((tipo, i) => (
                          <span key={i} className={`type-badge type-${tipo}`}>
                      {tipo}
                    </span>
                      ))}
                    </div>

                    {/* --- SEÇÃO DE STATUS (ORDEM PERSONALIZADA) --- */}
                    <div style={{display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '5px'}}>
                      {selectedPokemon.status ? (
                          statConfig.map((stat) => {
                            // Busca o valor no mapa de status usando a chave da API
                            const value = selectedPokemon.status[stat.key] || 0;

                            return (
                                <div key={stat.key} style={{display: 'flex', justifyContent: 'space-between', fontSize: '0.9rem', borderBottom: '1px dashed #ccc'}}>
                                  <span style={{fontWeight: 'bold', color: '#555'}}>{stat.label}</span>
                                  <span>{value}</span>
                                </div>
                            );
                          })
                      ) : (
                          <p>Carregando status...</p>
                      )}
                    </div>
                    {/* ----------------------- */}

                  </>
              ) : (
                  <p>Select a Pokemon</p>
              )}
            </div>

            <div className="footer-tabs">
              <div className="tab-icon" title="List"></div>
              <div className="tab-icon" title="Stats"></div>
              <div className="tab-icon" title="Moves"></div>
              <div className="tab-icon" title="Bag"></div>
            </div>

          </div>
        </div>
      </div>
  );
}

export default App;