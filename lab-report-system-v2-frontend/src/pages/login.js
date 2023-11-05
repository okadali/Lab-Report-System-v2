import { Button, Col, Input, Label, Row } from "reactstrap";

const LoginPage = () => {
  return (
    <div className="bg-gray-darker w-screen h-screen flex items-center justify-center">
      <div className="w-[450px]  p-[50px] bg-white rounded-3xl">
        <Row>
          <Label for="username">Name</Label>
          <Input id="username" type="text" />

          <Label for="password">Password</Label>
          <Input id="password" type="password" />

          <Button>Login</Button>
        </Row>
      </div>
    </div>
  );
};

export default LoginPage;
