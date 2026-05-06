import axios from 'axios';
//envia cookies
const api = axios.create({
    baseURL: 'http://localhost:420',
    withCredentials: true,
});

export default api;