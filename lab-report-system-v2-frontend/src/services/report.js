import ApiService, {API_URL} from "./api"

export const _getReports = async (searchRequest,page) => {
  const auth = localStorage.getItem("auth");

  let url = `${API_URL}/reports?size=10&page=${page}`;
  if(searchRequest.name) url += `&name=${searchRequest.name}`;
  if(searchRequest.surname) url += `&surname=${searchRequest.surname}`;
  if(searchRequest.tcId) url += `&tc_id=${searchRequest.tcId}`;
  if(searchRequest.userName) url += `&userName=${searchRequest.userName}`;
  if(searchRequest.userSurname) url += `&userSurname=${searchRequest.userSurname}`;
  if(searchRequest.sortByDate) url += `&sortByDate=${searchRequest.sortByDate}`;

  console.log({url});

  const config = {
    headers: {
      Authorization: `Bearer ${auth}`,
    }
  }

  const response = await ApiService.get(url,config);
  return response.data
}

export const _createReport = async (createReportRequest) => {
  const auth = localStorage.getItem("auth");
  const url = `${API_URL}/reports`;

  const config = {
    headers: {
      Authorization: `Bearer ${auth}`,
    }
  }

  const response = await ApiService.post(url, createReportRequest, config);
  return response.data;
}

export const _deleteReport = async (id) => {
  const auth = localStorage.getItem("auth");
  const url = `${API_URL}/reports/${id}`;

  const config = {
    headers: {
      Authorization: `Bearer ${auth}`,
    }
  }

  const response = await ApiService.delete(url, config);
  return response.data;
}

export const _updateReport = async (updateRequest,id) => {
  const auth = localStorage.getItem("auth");
  const url = `${API_URL}/reports/${id}`;

  const config = {
    headers: {
      Authorization: `Bearer ${auth}`,
    }
  }

  const response = await ApiService.put(url, updateRequest, config);
  return response.data;
}
