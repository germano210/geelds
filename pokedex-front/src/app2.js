import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [pokemons, setPokemons] = useState([]);
  useEffect(() => {
    const fetchPokemons = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/pokemons');
        setPokemons(response.data);
      } catch (error) {
        console.error("Erro ao buscar os pokémons:", error);
      }
    };
    fetchPokemons();
  }, []);
  return (
    <div className="App">
      <h1>Pokédex Simples</h1>
      <div className="pokedex-container">
        {pokemons.map((pokemon, index) => (
          <div key={index} className="pokemon-card">
            <img src={pokemon.imageURL} alt={pokemon.nome} />
            <h2>{pokemon.nome}</h2>
            <div className="pokemon-types">
              {pokemon.tipos.map((tipo, i) => (
                <span key={i} className={`pokemon-type type-${tipo}`}>
                  {tipo}
                </span>
              ))}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;