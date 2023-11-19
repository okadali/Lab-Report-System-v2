import { useEffect, useState } from "react";
import { Button, Col, Input, Modal } from "reactstrap";
import { _uploadFile } from "../services/file";
import { _createReport } from "../services/report";
import { useGlobalContext } from "../context/context";

const CreateModal = ({ isOpen, toggle }) => {
  const [createdReport, setCreatedReport] = useState({});
  const [selectedFile, setSelectedFile] = useState(null);

  const {setRefresh} = useGlobalContext();

  useEffect(() => {
    setCreatedReport({
      name: "",
      surname: "",
      tc_id: "",
      title: "",
      details: "",
    });
  }, [isOpen]);

  const onCreateClick = async () => {
    try {
      const { name, surname, tc_id, title, details } = createdReport;
      console.log(localStorage.getItem("auth"));

      if (!(name && surname && tc_id && title && details && selectedFile)) {
        alert("Please fill all fields");
        return;
      }

      const imageUploadResponse = await _uploadFile(selectedFile);

      if (!imageUploadResponse.success) {
        alert("Error occured file image upload it my be too big");
        return;
      }

      createdReport.imageDataCode = imageUploadResponse.data;
      setCreatedReport({...createdReport});
      const reportCreateResponse = await _createReport(createdReport);
      if(reportCreateResponse.success) {
        alert("Report created successfully");
        setRefresh(true);
        toggle();
      }
    } 
    catch (e) {
      console.log({ e });
    }
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
          {INPUT_TYPES.map(({ id, placeholder, type }) => {
            return (
              <Input
                key={id}
                id={id}
                placeholder={placeholder}
                type={type}
                className="mb-[20px]"
                onChange={onInputChange}
              />
            );
          })}
          <Input
            type="file"
            accept="image/jpeg, image/png"
            onChange={handleFileChange}
          ></Input>
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
