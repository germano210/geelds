import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [pokemons, setPokemons] = useState([]);
  const [selectedPokemon, setSelectedPokemon] = useState(null);

  useEffect(() => {
    const fetchPokemons = async () => {
      try {
        const response = await axios.get('http://localhost:420/api/pokemons');
        setPokemons(response.data);
        if (response.data.length > 0) {
        }
      } catch (error) {
        console.error("Error fetching pokemons:", error);
        setPokemons([]);
      }
    }
    fetchPokemons();
  }, []);

  const listaShinyFixa = ['cloyster', 'golem'];

  return (
      <div className="App">
        <h1>Pokédex Aero System</h1>

        {/* Container Dividido */}
        <div className="main-layout">

          {/* --- LADO ESQUERDO: GRID --- */}
          <div className="left-pane glass-panel">
            <div className="pokedex-grid">
              {pokemons.map((pokemon, index) => {
                const imagemURL = listaShinyFixa.includes(pokemon.nome)
                    ? pokemon.imageShinyURL
                    : pokemon.imageURL;

                return (
                    <div
                        key={index}
                        className="pokemon-item"
                        onClick={() => setSelectedPokemon({...pokemon, imagemDisplay: imagemURL})}
                    >
                      <img src={imagemURL} alt={pokemon.nome} />
                      <h4>#{pokemon.numero}</h4>
                      <strong>{pokemon.nome}</strong>
                    </div>
                )
              })}
            </div>
          </div>

          {/* --- LADO DIREITO: DETALHES --- */}
          <div className="right-pane glass-panel">
            {selectedPokemon ? (
                <>
                  {/* Aguardando expandir as builds */}
                  <h2 style={{fontSize: '2em', marginBottom: '10px'}}>
                    #{selectedPokemon.numero} {selectedPokemon.nome}
                  </h2>

                  <img
                      src={selectedPokemon.imagemDisplay}
                      alt={selectedPokemon.nome}
                      className="detail-image"
                  />

                  <div className="pokemon-tipos">
                    {selectedPokemon.tipos.map((tipo, i) => (
                        <span key={i} className={`pokemon-type-badge tipo-${tipo}`}>
                    {tipo}
                  </span>
                    ))}
                  </div>

                  <div style={{marginTop: '30px', background: 'rgba(0,0,0,0.3)', padding: '15px', borderRadius: '10px', width: '80%'}}>
                    <h3>Detalhes da Espécie</h3>
                    <p>Aqui colocaremos a descrição, egg groups e habilidades que vêm do back-end.</p>
                  </div>
                </>
            ) : (
                <div style={{opacity: 0.7}}>
                  <h3>Selecione um Pokémon</h3>
                  <p>Clique na lista à esquerda para ver os dados.</p>
                </div>
            )}
          </div>

        </div>
      </div>
  );
}

export default App;