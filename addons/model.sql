-- 1. Usuários (Com Preferências de Interface)
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password_hash TEXT NOT NULL,
                       dark_mode BOOLEAN DEFAULT FALSE,
                       real_mode BOOLEAN DEFAULT FALSE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Pokémon Vistos
CREATE TABLE user_seen_pokemon (
                                   id SERIAL PRIMARY KEY,
                                   user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
                                   pokemon_id INTEGER NOT NULL,
                                   seen_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   UNIQUE(user_id, pokemon_id)
);

-- 3. Favoritos
CREATE TABLE user_favorites (
                                id SERIAL PRIMARY KEY,
                                user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
                                pokemon_id INTEGER NOT NULL,
                                favorited_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                UNIQUE(user_id, pokemon_id)
);

-- 4. Cuidadores
CREATE TABLE pokemon_moderators (
                                    id SERIAL PRIMARY KEY,
                                    user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
                                    pokemon_id INTEGER NOT NULL,
                                    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    UNIQUE(user_id, pokemon_id)
);

-- 5. Builds
CREATE TABLE pokemon_builds (
                                id SERIAL PRIMARY KEY,
                                user_id INTEGER REFERENCES users(id) ON DELETE SET NULL,
                                pokemon_id INTEGER NOT NULL,
                                build_name VARCHAR(100) NOT NULL,
                                guide_text TEXT,
                                item VARCHAR(100), ability VARCHAR(100), nature VARCHAR(50),
                                level INTEGER DEFAULT 100, tera_type VARCHAR(50), gender VARCHAR(1),
                                happiness INTEGER DEFAULT 255, is_shiny BOOLEAN DEFAULT FALSE,
                                ev_hp INTEGER DEFAULT 0, ev_atk INTEGER DEFAULT 0, ev_def INTEGER DEFAULT 0,
                                ev_spa INTEGER DEFAULT 0, ev_spd INTEGER DEFAULT 0, ev_spe INTEGER DEFAULT 0,
                                iv_hp INTEGER DEFAULT 31, iv_atk INTEGER DEFAULT 31, iv_def INTEGER DEFAULT 31,
                                iv_spa INTEGER DEFAULT 31, iv_spd INTEGER DEFAULT 31, iv_spe INTEGER DEFAULT 31,
                                generation INTEGER DEFAULT 9,
                                status VARCHAR(20) DEFAULT 'PENDING',
                                vote_count INTEGER DEFAULT 0,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 6. Golpes
CREATE TABLE build_moves (
                             id SERIAL PRIMARY KEY,
                             build_id INTEGER REFERENCES pokemon_builds(id) ON DELETE CASCADE,
                             move_name VARCHAR(100) NOT NULL
);

-- 7. Votos
CREATE TABLE build_votes (
                             id SERIAL PRIMARY KEY,
                             user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
                             build_id INTEGER REFERENCES pokemon_builds(id) ON DELETE CASCADE,
                             vote_type INTEGER DEFAULT 1,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             UNIQUE(user_id, build_id)
);

-- 8. Comentários
CREATE TABLE build_comments (
                                id SERIAL PRIMARY KEY,
                                build_id INTEGER REFERENCES pokemon_builds(id) ON DELETE CASCADE,
                                user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
                                parent_comment_id INTEGER REFERENCES build_comments(id) ON DELETE CASCADE,
                                content TEXT NOT NULL,
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 9. Dicionário de Filtro
CREATE TABLE forbidden_words (
                                 id SERIAL PRIMARY KEY,
                                 word VARCHAR(100) UNIQUE NOT NULL
);