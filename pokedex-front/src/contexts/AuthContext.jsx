import { createContext, useState, useEffect } from 'react';
import api from '../services/api';

export const AuthContext = createContext({});

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // verifica se ta logado
        api.get('/api/auth/me')
            .then((response) => {
                setUser(response.data); // Se o cookie for valido salva o usuário
            })
            .catch(() => {
                setUser(null); // Se n tiver for, deixa nulo
            })
            .finally(() => {
                setLoading(false);
            });
    }, []);

    const signIn = async (email, password) => {
        await api.post('/api/auth/login', { email, password });
        const response = await api.get('/api/auth/me');
        setUser(response.data);
    };

    return (
        <AuthContext.Provider value={{ user, loading, signIn }}>
            {children}
        </AuthContext.Provider>
    );
};