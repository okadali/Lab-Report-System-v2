import { useEffect } from "react";
import { useGlobalContext } from "../context/context";
import SearchBar from "../components/search";

const ReportPage = () => {
  const { auth, setAuth } = useGlobalContext();

  useEffect(() => {

  }, [auth]);

  return (
    <div>
      <SearchBar/>
    </div>
  );
};
export default ReportPage;
