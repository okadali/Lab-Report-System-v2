import { useState } from "react";
import { Button, Col, Input, Label, Row } from "reactstrap";
import { _getReports } from "../services/report";

const SearchBar = () => {
  const [searchRequest, setSearchRequest] = useState({
    name: "",
    surname: "",
    tcId: "",
    userName: "",
    userSurname: "",
    sortByDate: false,
    size: 10,
    page: 0,
  });

  const onSearchButtonClick = async() => {
    const response = await _getReports(searchRequest)
    try {
      console.log({response});
    }
    catch(error) {
      console.log({error});
    }
    
  };

  const onInputChange = (e) => {
    setSearchRequest({ ...searchRequest, [e.target.id]: e.target.value });
  };

  return (
    <div>
      <Row>
        <Col md={4}>
          <Label for="name">Name</Label>
          <Input
            id="name"
            name="name"
            placeholder="Patients name"
            type="text"
            onChange={onInputChange}
          />
        </Col>
        <Col md={4}>
          <Label for="surname">Surname</Label>
          <Input
            id="surname"
            name="surname"
            placeholder="Patients surname"
            type="text"
            onChange={onInputChange}
          />
        </Col>
        <Col md={4}>
          <Label for="tcId">T.C. Id</Label>
          <Input
            id="tcId"
            name="surname"
            placeholder="Patients T.C. Id"
            type="text"
            onChange={onInputChange}
          />
        </Col>
      </Row>
      <Row>
        <Col md={5}>
          <Label for="userName">Laboratorian Name</Label>
          <Input
            id="userName"
            name="name"
            placeholder="Patients name"
            type="text"
            onChange={onInputChange}
          />
        </Col>
        <Col md={5}>
          <Label for="userSurname">Laboratorian Surname</Label>
          <Input
            id="userSurname"
            name="surname"
            placeholder="Patients surname"
            type="text"
            onChange={onInputChange}
          />
        </Col>
        <Col md={2}>
          <Label check for="exampleCheck">
            Sort By Date
          </Label>
          <Input
            id="exampleCheck"
            name="check"
            type="checkbox"
            value={searchRequest.sortByDate}
            onChange={() => {setSearchRequest({...searchRequest,sortByDate:!searchRequest.sortByDate})}}
          />
          <Button className="ml-[20px]" onClick={onSearchButtonClick}>
            Search
          </Button>
        </Col>
      </Row>
    </div>
  );
};

export default SearchBar;
