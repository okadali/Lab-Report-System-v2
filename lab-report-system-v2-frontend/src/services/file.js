import ApiService, {API_URL} from "./api"

export const _uploadFile = async (file) => { 
  var formData = new FormData()
  formData.append('image', file)

  const auth = localStorage.getItem("auth");

  const config = {
    headers: {
      Authorization: `Bearer ${auth}`,
      'Content-Type': 'multipart/form-data',
    }
  }

  const response = await ApiService.post(`${API_URL}/file`, formData, config)
  return response.data;
}

export const _updateFile = async (file,id) => {
  var formData = new FormData()
  formData.append('image', file)

  const url = `${API_URL}/file/${id}`;
  console.log({url});

  const auth = localStorage.getItem("auth");

  const config = {
    headers: {
      Authorization: `Bearer ${auth}`,
      'Content-Type': 'multipart/form-data',
    }
  }

  const response = await ApiService.put(url, formData, config)
  return response.data;
}