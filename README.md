#  Pokédex & Competitive Build Manager

Um projeto *fullstack* que combina uma Pokédex interativa com um gestor de *builds* competitivas de Pokémon (baseado no formato Smogon). 

Este projeto foi desenvolvido com o intuito de criar um similar util ao smogon, integrando uma API RESTful robusta com uma interface de utilizador dinâmica e responsiva.

##  Tecnologias Utilizadas

**Frontend:**
* [React](https://reactjs.org/) - Biblioteca JavaScript para construção da interface.
* CSS3 / HTML5 - Estilização e estrutura.
* Integração com a [PokéAPI](https://pokeapi.co/) para recolha de dados e *sprites*.

**Backend:**
* [Java](https://www.java.com/) - Linguagem principal.
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework para a criação da API REST.
* Maven - Gestor de dependências e *build*.

##  Funcionalidades

### Atuais
* **Pesquisa de Pokémon:** Busca em tempo real por nome ou número na PokéAPI.
* **Navegação Intuitiva:** Botões para avançar e retroceder na listagem.
* **Interface Clássica:** Design inspirado na Pokédex original.
* **API de Builds Competitivas:** Capacidade de importar *builds* no formato texto do *Pokémon Showdown* (Smogon) e .
* **Sistema de Autenticação:** Criação de perfis de utilizador para que cada pessoa possa guardar e gerir as suas próprias estratégias e comentarios.

### Em Desenvolvimento (Próximos Passos)
* [ ] **Persistência de Dados:** Integração com base de dados relacional (PostgreSQL) para guardar as *builds* personalizadas de forma rápida, reduzindo a dependência de chamadas externas.
* [ ] **Sistema de Login:** Arrumar como login será feito e como é gerenciado as autenticaçoes.
* [ ] **Fazer tudo dinamico:** Deixar a usabilidade pratia.

##  Como Executar o Projeto Localmente

Para rodar precisa de: [Node.js](https://nodejs.org/) e [Java JDK](https://www.oracle.com/java/technologies/downloads/).

### 1. Clonar o repositório
```bash
git clone [https://github.com/teu-usuario/teu-repositorio.git](https://github.com/teu-usuario/teu-repositorio.git)
