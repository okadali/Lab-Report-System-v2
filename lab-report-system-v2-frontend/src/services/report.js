import ApiService, {API_URL} from "./api"

export const _getReports = async (searchRequest) => {
  const auth = localStorage.getItem("auth");
  const url = `${API_URL}/reports?size=${searchRequest.size}&page=${searchRequest.page}`;
  console.log({url});
  const headers = {'Authorization': `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJva2FkYWxpIiwiaWF0IjoxNjk5NzI2MTE2LCJleHAiOjE2OTk3Mjk3MTZ9.nMAIKzamwsh1rPDFdEX92nRUxExeiXyWL-UkKe3KfKg`}
  const response = await ApiService.get(url,{headers});
  return response.data
}