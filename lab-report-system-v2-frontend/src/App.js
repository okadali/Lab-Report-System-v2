import { Routes,Route } from "react-router-dom";
import LoginPage from "./pages/login";
import ReportPage from "./pages/report";
import RegisterPage from "./pages/register";
import { useEffect } from "react";

localStorage.clear();

function App() {
  return <Routes>
    <Route path="/" element={<LoginPage/>}/>
    <Route path="/register" element={<RegisterPage/>}/>
    <Route path="/reports" element={<ReportPage/>}/>
  </Routes>
}

export default App;
