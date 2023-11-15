import { useEffect, useState } from "react";
import { Button, Col, Input, Modal } from "reactstrap";

const CreateModal = ({ isOpen, toggle }) => {

  const [createdReport,setCreatedReport] = useState({})

  useEffect(() => {
    setCreatedReport({
      name:"",
      surname:"",
      tc_id:"",
      title:"",
      details:""
    })
  },[isOpen])

  const onCreateClick = () => {
    console.log({createdReport});
    //const response =  _uploadFile()
    // if(response.status == 200)
    // const createResponse _createReport(respone.data.data)
  };

  const INPUT_TYPES = [
    {
      id: "name",
      placeholder: "Patients Name",
      type: "text",
    },
    {
      id: "surname",
      placeholder: "Patients Surname",
      type: "text",
    },
    {
      id: "tc_id",
      placeholder: "Tc. ID",
      type: "text",
    },
    {
      id: "title",
      placeholder: "Title",
      type: "text",
    },
    {
      id: "details",
      placeholder: "Details",
      type: "textarea",
    },
  ];

  const onInputChange = (e) => {
    setCreatedReport({ ...createdReport, [e.target.id]: e.target.value });
  };

  return (
    <Modal isOpen={isOpen} toggle={toggle}>
      <div className="p-[15px]">
        <Col>
          {INPUT_TYPES.map(({id,placeholder,type}) => {
            return <Input
              id={id}
              placeholder={placeholder}
              type={type}
              className="mb-[20px]"
              onChange={onInputChange}
            />
          })}
          <Input type="file"></Input>
          <div className="flex justify-end">
            <Button color="primary" onClick={onCreateClick}>
              Create
            </Button>
          </div>
        </Col>
      </div>
    </Modal>
  );
};

export default CreateModal;
