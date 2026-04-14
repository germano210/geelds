    -- 1. Tabela de Usuários
    CREATE TABLE users (
                           id SERIAL PRIMARY KEY,
                           username VARCHAR(50) UNIQUE NOT NULL,
                           email VARCHAR(100) UNIQUE NOT NULL,
                           password_hash TEXT NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    -- 2. Tabela de Builds (AGORA COMPLETA)
    CREATE TABLE pokemon_builds (
                                    id SERIAL PRIMARY KEY,
                                    user_id INTEGER REFERENCES users(id),
                                    pokemon_id INTEGER NOT NULL,
                                    build_name VARCHAR(100) NOT NULL,
                                    item VARCHAR(100),
                                    ability VARCHAR(100),
                                    nature VARCHAR(50),

        -- Novos Campos Competitivos
                                    level INTEGER DEFAULT 100,            -- Nível (Padrão 100)
                                    tera_type VARCHAR(50),                -- Ex: "Flying", "Steel"
                                    gender VARCHAR(1),                    -- 'M', 'F' ou Nulo (Genderless)
                                    happiness INTEGER DEFAULT 255,        -- Padrão máximo para golpes como Return
                                    is_shiny BOOLEAN DEFAULT FALSE,       -- Define se o sprite renderizado será o shiny

        -- EVs
                                    ev_hp INTEGER DEFAULT 0, ev_atk INTEGER DEFAULT 0, ev_def INTEGER DEFAULT 0,
                                    ev_spa INTEGER DEFAULT 0, ev_spd INTEGER DEFAULT 0, ev_spe INTEGER DEFAULT 0,

        -- IVs
                                    iv_hp INTEGER DEFAULT 31, iv_atk INTEGER DEFAULT 31, iv_def INTEGER DEFAULT 31,
                                    iv_spa INTEGER DEFAULT 31, iv_spd INTEGER DEFAULT 31, iv_spe INTEGER DEFAULT 31,

                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    -- 3. Tabela de Moves
    CREATE TABLE build_moves (
                                 id SERIAL PRIMARY KEY,
                                 build_id INTEGER REFERENCES pokemon_builds(id) ON DELETE CASCADE,
                                 move_name VARCHAR(100) NOT NULL
    );