import { useEffect, useState } from "react";
import { Button, Table } from "reactstrap";
import EditModal from "./EditModal";
import { _deleteReport } from "../services/report";
import { useGlobalContext } from "../context/context";
import { getProperYear } from "../helper/getProperYear";


const Content = () => {
  const {reports,setRefresh} = useGlobalContext();

  const [editModal, setEditModal] = useState(false);

  const [selectedReport, setSelectedReport] = useState({});

  useEffect(() => {
    setRefresh(true);
  }, []);

  const onEditClick = (report) => {
    setSelectedReport(report);
    setEditModal(true);
  };

  const onDeleteClick = async (report) => {
    try {
      const response = await _deleteReport(report.id);
      if(response.success) setRefresh(true);
      else throw new Error();
    } catch(e) {
      alert("You're not authorized to do that");
    }
  };

  return (
    <div className="mt-[30px]">
      <EditModal
        isOpen={editModal}
        toggle={() => setEditModal(!editModal)}
        selectedReport={selectedReport}
      />

      <Table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Surname</th>
            <th>TC. ID</th>
            <th>Title</th>
            <th>Details</th>
            <th>Date</th>
            <th>Labor. Name</th>
            <th>Labor. Surname</th>
            <th>Report</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {reports.map((item) => {
            const imageUrl = `http://localhost:8081/api/v1/file/${item.imageDataCode}?id =${Date.now().toString()}`;
            return (
              <tr key={item.id}>
                <th>{item.id}</th>
                <th>{item.name}</th>
                <th>{item.surname}</th>
                <th>{item.tc_id}</th>
                <th>{item.title}</th>
                <th>{item.details}</th>
                <th>{getProperYear(item.creationDate)}</th>
                <th>{item.doctorName}</th>
                <th>{item.doctorSurname}</th>
                <th>
                  <a href={imageUrl} target="_blank">
                    <img src={imageUrl} width={30} height={30} alt="" />
                  </a>
                </th>
                <th>
                  <div className="flex">
                    <Button color="primary" onClick={() => onEditClick(item)}>
                      Edit
                    </Button>
                    <Button
                      className="ml-[10px]"
                      color="danger"
                      onClick={() => onDeleteClick(item)}
                    >
                      Delete
                    </Button>
                  </div>
                </th>
              </tr>
            );
          })}
        </tbody>
      </Table>
    </div>
  );
};

export default Content;
