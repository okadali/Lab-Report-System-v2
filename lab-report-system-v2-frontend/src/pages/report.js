import { useState } from "react";
import SearchBar from "../components/search";
import Content from "../components/content";
import { Button } from "reactstrap";
import CreateModal from "../components/createModal";
import PaginationHolder from "../components/pagination";

const ReportPage = () => {

  const [createModal,setCreateModal] = useState(false);

  const onCreateButtonClick = () => setCreateModal(true)

  return (
    <div>
      <CreateModal isOpen={createModal} toggle={() => setCreateModal(!createModal)}/>
      <SearchBar/>
      <Button className="mt-[40px]" color="primary" onClick={onCreateButtonClick}>Create Report</Button>
      <Content/>
      <PaginationHolder/>
    </div>
  );
};
export default ReportPage;
