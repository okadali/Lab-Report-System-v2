import React, { useState, useContext} from "react";


const AppContext = React.createContext();

const AppProvider = ({ children }) => {
  const [auth,setAuth] = useState("");
  
    return (
      <AppContext.Provider
        value={{
          auth,setAuth
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