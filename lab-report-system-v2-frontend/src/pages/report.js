import { useEffect, useState } from "react";
import { useGlobalContext } from "../context/context";
import SearchBar from "../components/search";
import Content from "../components/content";
import { Button } from "reactstrap";
import CreateModal from "../components/createModal";

const ReportPage = () => {

  const [createModal,setCreateModal] = useState(false);

  const onCreateButtonClick = () => setCreateModal(true)

  return (
    <div>
      <CreateModal isOpen={createModal} toggle={() => setCreateModal(!createModal)}/>
      <SearchBar/>
      <Button className="mt-[40px]" color="primary" onClick={onCreateButtonClick}>Create Report</Button>
      <Content/>
    </div>
  );
};
export default ReportPage;
