import { useEffect, useState } from "react";
import { Button, Col, Input, Modal } from "reactstrap";
import { _updateFile } from "../services/file";
import { _updateReport } from "../services/report";
import { useGlobalContext } from "../context/context";

const EditModal = ({ isOpen, toggle, selectedReport }) => {

  const {setRefresh} = useGlobalContext();

  const [editedReport, setEditedReport] = useState({});
  const [selectedFile, setSelectedFile] = useState(null);

  useEffect(() => {
    setEditedReport({});
  }, [selectedReport,isOpen]);


  const onUpdateClick = async () => {
    if(selectedFile) {
      try {
        await _updateFile(selectedFile,selectedReport.imageDataCode);
      } catch(e) {
        alert("Error occured file image upload it my be too big");
        return;
      }
    }

    try {
      await _updateReport(editedReport,selectedReport.id);
    } catch(e) {
      alert("Error occured while updating report");
      return;
    }

    setRefresh(true);
    toggle();
  };

  const onInputChange = (e) => {
    setEditedReport({ ...editedReport, [e.target.id]: e.target.value });
  };
  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      const isJpegOrPng =
        file.type === "image/jpeg" || file.type === "image/png";
      if (isJpegOrPng) setSelectedFile(file);
      else alert("Please select a valid JPEG or PNG file.");
    }
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
              key={id}
              id={id}
              placeholder={placeholder}
              type={type}
              value={editedReport[id] ?? selectedReport[id]}
              onChange={onInputChange}
              className="mb-[20px]"
            />
          })}
          <Input
            type="file"
            accept="image/jpeg, image/png"
            onChange={handleFileChange}
          />
          <div className="flex justify-end">
            <Button color="primary" className="mt-[15px]" onClick={onUpdateClick} >
              Update
            </Button>
          </div>
        </Col>
      </div>
    </Modal>
  );
};

export default EditModal;
