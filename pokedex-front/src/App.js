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

  const listaShiny = ['cloyster','golem'];
  return (
    <div className="App">
      <h1>Extrato de Pokédex</h1>
      <div className="pokedex-container">
        {pokemon.map((pokemon, index)=>{
          const imagemURL = listaShiny.includes(pokemon.nome)?pokemon.imageShinyURL:pokemon.imageURL;
          return (
          <div key={index} className="pokemon-card">
            <h4>#{pokemon.numero}</h4>
            <img src={imagemURL} alt={pokemon.nome}/>
            <h2>{pokemon.nome}</h2>
            <div className="pokemon-tipos">
              {pokemon.tipos.map((tipo, i)=>(
                <span key ={i} className={`pokemon-tipos tipo-${tipo}`}>
                  {tipo}
                </span> 
              ))}
            </div>
          </div>
          )
        })}
      </div>
    </div>
  );
}
export default App;