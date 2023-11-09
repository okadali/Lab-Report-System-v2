import ApiService, {API_URL} from "./api"

export const _login = async (hospitalIdAndPassword) => {
    const url = `${API_URL}/auth/authenticate`;
    const response = await ApiService.post(url,hospitalIdAndPassword);
    return response.data
}

export const _register = async (registerRequest) => {
    const url = `${API_URL}/auth/register`;
    const response = await ApiService.post(url,registerRequest);
    return response.data
}