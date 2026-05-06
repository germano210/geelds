import { useContext } from 'react';
import { Navigate } from 'react-router-dom';
import { AuthContext } from '../contexts/AuthContext';

export const PrivateRoute = ({ children }) => {
    const { user, loading } = useContext(AuthContext);

    // tela de loading
    if (loading) {
        return <div>Carregando a sua Pokédex...</div>;
    }

    // se nao achou cookies volta pro login
    if (!user) {
        return <Navigate to="/login" replace />;
    }

    // Se tem tudo, libera o acesso à tela !
    return children;
};