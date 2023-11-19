import { useEffect, useState } from "react";
import { Button, ButtonGroup, Col, Input, Label, Row } from "reactstrap";
import { _register } from "../services/auth";
import { useNavigate } from "react-router-dom";

const RegisterPage = () => {

  const navigate = useNavigate();

  useEffect(() => {
    const auth = localStorage.getItem("auth")
    if(auth) navigate("/reports")
  },[])

  const registerObject = {
    name: "",
    surname: "",
    hospitalId: "",
    password: "",
    role: "USER",
  }

  const [registerRequest, setRegisterRequest] = useState(registerObject);

  const onInputChange = (e) => {
    setRegisterRequest({ ...registerRequest, [e.target.id]: e.target.value });
  };

  const onRegisterButtonClick = async () => {
    try {
      const response = await _register(registerRequest);
      setRegisterRequest(registerObject);
      console.log([{response}]);
      localStorage.setItem("auth",response.data.token);
      navigate("/reports");
    } catch (e) {
      alert(e.response.data.message);
    }
  };

  return (
    <div className="bg-gray-darker w-screen h-screen flex items-center justify-center">
      <div className="w-[450px]  p-[50px] bg-white rounded-3xl">
        <Col>
          <Row className="mb-[20px]">
            <Label for="name">Name</Label>
            <Input id="name" onChange={onInputChange} type="text" />
          </Row>
          <Row className="mb-[20px]">
            <Label for="surname">Surname</Label>
            <Input id="surname" onChange={onInputChange} type="text" />
          </Row>
          <Row className="mb-[20px]">
            <Label for="username">Hospital ID</Label>
            <Input id="hospitalId" onChange={onInputChange} type="text" />
          </Row>
          <Row className="mb-[20px]">
            <Label for="password">Password</Label>
            <Input id="password" onChange={onInputChange} type="password" />
          </Row>
          <Row>
            <ButtonGroup className="mb-[20px]">
              <Button
                color="primary"
                outline
                id="role"
                onClick={() => {
                  setRegisterRequest({ ...registerRequest, role: "USER" });
                }}
                active={registerRequest.role === "USER"}
              >
                USER
              </Button>
              <Button
                color="primary"
                outline
                onClick={() => {
                  setRegisterRequest({ ...registerRequest, role: "ADMIN" });
                }}
                active={registerRequest.role === "ADMIN"}
              >
                ADMIN
              </Button>
            </ButtonGroup>
          </Row>
          <Row>
            <Button onClick={onRegisterButtonClick}>Register</Button>
          </Row>
        </Col>
      </div>
    </div>
  );
};

export default RegisterPage;
