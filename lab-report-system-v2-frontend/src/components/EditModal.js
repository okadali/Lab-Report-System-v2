import { useEffect, useState } from "react";
import { Button, Col, Input, Modal } from "reactstrap";

const EditModal = ({ isOpen, toggle, selectedReport }) => {

  const [editedReport, setEditedReport] = useState({});

  useEffect(() => {
    setEditedReport({
      name: selectedReport.name,
      surname: selectedReport.surname,
      tc_id: selectedReport.tc_id,
      title: selectedReport.title,
      details: selectedReport.details,
      date: selectedReport.date
    });
  }, [selectedReport,isOpen]);


  const onUpdateClick = () => {
    console.log({
      editedReport,
    });
  };

  const onInputChange = (e) => {
    setEditedReport({ ...editedReport, [e.target.id]: e.target.value });
  };


  const INPUT_TYPES = [
    {
      id:"name",
      placeholder:"Patients Name",
      type:"text",
    },
    {
      id:"surname",
      placeholder:"Patients Surname",
      type:"text",
    },
    {
      id:"tc_id",
      placeholder:"Tc. ID",
      type:"text",
    },
    {
      id:"title",
      placeholder:"Title",
      type:"text",
    },
    {
      id:"details",
      placeholder:"Details",
      type:"textarea",
    }
  ]

  return (
    <Modal isOpen={isOpen} toggle={toggle}>
      <div className="p-[15px]">
        <Col>
          {INPUT_TYPES.map(({id,placeholder,type}) => {
            return <Input
              id={id}
              placeholder={placeholder}
              type={type}
              value={editedReport[id]}
              onChange={onInputChange}
              className="mb-[20px]"
            />
          })}
          <div className="flex justify-end">
            <Button color="primary" onClick={onUpdateClick} >
              Update
            </Button>
          </div>
        </Col>
      </div>
    </Modal>
  );
};

export default EditModal;
