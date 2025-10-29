import React, {useState, useEffect} from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [pokemon, setPokemons] = useState([]);
  useEffect(() => {
    const fetchPokemons = async () => {
      try{
        const response = await axios.get('http://localhost:420/api/pokemons');
        setPokemons(response.data);      
      }catch (error){
        console.error("Error fetching pokemons:", error);
        setPokemons([]);
      }
    }
    fetchPokemons();
  }, []);
  return (
    <div className="App">
      <h1>Extrato de Pokédex</h1>
      <div className="pokedex-container">
        {pokemon.map((pokemon, index)=>(
          <div key={index} className="pokemon-card">
            <h4>#{pokemon.numero}</h4>
            <img src={pokemon.imageURL} alt={pokemon.nome}/>
            <h2>{pokemon.nome}</h2>
            <div className="pokemon-tipos">
              {pokemon.tipos.map((tipo, i)=>(
                <span key ={i} className={`pokemon-tipos tipo-${tipo}`}>
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