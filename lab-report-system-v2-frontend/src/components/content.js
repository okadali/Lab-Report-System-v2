import { useEffect, useState } from "react";
import { PLACEHOLDER_DATA } from "../data/placeholder_data";
import { Button, Table } from "reactstrap";
import EditModal from "./EditModal";


const Content = () => {
  const [reports, setReports] = useState([]);

  const [editModal, setEditModal] = useState(false);



  const [selectedReport, setSelectedReport] = useState({});

  useEffect(() => {
    const loadData = async () => {
      setReports(PLACEHOLDER_DATA);
    };
    loadData();
  }, []);

  const onEditClick = (report) => {
    setSelectedReport(report);
    setEditModal(true);
  };

  const onDeleteClick = (report) => {
    console.log(report.id);

    // const response = _deleteReport(report.id);
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
            <th>Doctor Name</th>
            <th>Report</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {reports.map((item) => {
            return (
              <tr key={item.id}>
                <th>{item.id}</th>
                <th>{item.name}</th>
                <th>{item.surname}</th>
                <th>{item.tc_id}</th>
                <th>{item.title}</th>
                <th>{item.details}</th>
                <th>{item.date.toString()}</th>
                <th>{item.user_name}</th>
                <th>
                  <img src={item.imageDataCode} width={50} height={50} alt="" />
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
