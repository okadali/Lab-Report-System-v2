import { Button, Col, Input, Label, Row } from "reactstrap";
import { useEffect, useState } from "react";
import { _login } from "../services/auth";

const LoginPage = () => {

  useEffect(() => {
    const auth = localStorage.getItem("auth")
    if(auth) window.location.href = "/reports";
  },[])

  const loginObject = {hospitalId:"",password:""}

  const [loginRequest,setLoginRequest] = useState(loginObject);

  const onInputChange = (e) => {
    setLoginRequest({...loginRequest,[e.target.id]:e.target.value});
  }

  const onLoginButtonClick = async () => {
    try {
      const response = await _login(loginRequest);
      setLoginRequest(loginObject);
      localStorage.setItem("auth",response.data.token);
    } catch(e) {
      alert("invalid creds");
    }
  }

  return (
    <div className="bg-gray-darker w-screen h-screen flex items-center justify-center">
      <div className="w-[450px]  p-[50px] bg-white rounded-3xl">
        <Col>
          <Row className="mb-[20px]">
            <Label for="username">Hospital ID</Label>
            <Input id="hospitalId" onChange={onInputChange}  type="text" />
          </Row>
          <Row className="mb-[20px]">
            <Label for="password">Password</Label>
            <Input id="password" onChange={onInputChange} type="password" />
          </Row>
          <Row>
            <a href="/register">register</a>
          </Row>
          <Row>
            <Button onClick={onLoginButtonClick}>Login</Button>
          </Row>
        </Col>
      </div>
    </div>
  );
};

export default LoginPage;
