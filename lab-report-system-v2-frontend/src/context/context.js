import React, { useState, useContext} from "react";


const AppContext = React.createContext();

const AppProvider = ({ children }) => {
  const [auth,setAuth] = useState("");
  const [refresh,setRefresh] = useState(false);
  const [reports,setReports] = useState([]);
  const [pagination,setPagination] = useState({
    page:0,
    size:10,
    totalPage:-1
  });
  
    return (
      <AppContext.Provider
        value={{
          auth,setAuth,
          refresh,setRefresh,
          reports,setReports,
          pagination,setPagination
        }}
      >
        {children}
      </AppContext.Provider>
    );
  };


export const useGlobalContext = () => {
    return useContext(AppContext);
};
  
export { AppContext, AppProvider };