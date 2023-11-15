import { useEffect } from "react";
import { useGlobalContext } from "../context/context";
import SearchBar from "../components/search";
import Content from "../components/content";

const ReportPage = () => {
  const { auth, setAuth } = useGlobalContext();

  useEffect(() => {

  }, [auth]);

  return (
    <div>
      <SearchBar/>
      <Content/>
    </div>
  );
};
export default ReportPage;
