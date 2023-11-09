import axios from 'axios';

export const API_URL = 'http://localhost:8081/api/v1';
const ApiService = axios.create();

export default ApiService;