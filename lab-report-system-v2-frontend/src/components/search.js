const SearchBar = () => {
    return <div>
        <Row>
        <Col md={4}>
          <Label for="patientName">Name</Label>
          <Input
            id="patientName"
            name="name"
            placeholder="Patients name"
            type="text"
          />
        </Col>
        <Col md={4}>
          <Label for="patientSurname">Surname</Label>
          <Input
            id="patientSurname"
            name="surname"
            placeholder="Patients surname"
            type="text"
          />
        </Col>
        <Col md={4}>
          <Label for="patientTcid">T.C. Id</Label>
          <Input
            id="patientTcid"
            name="surname"
            placeholder="Patients T.C. Id"
            type="text"
          />
        </Col>
      </Row>
      <Row>
        <Col md={5}>
          <Label for="laboratorianName">Laboratorian Name</Label>
          <Input
            id="laboratorianName"
            name="name"
            placeholder="Patients name"
            type="text"
          />
        </Col>
        <Col md={5}>
          <Label for="laboratorianSurname">Laboratorian Surname</Label>
          <Input
            id="laboratorianSurname"
            name="surname"
            placeholder="Patients surname"
            type="text"
          />
        </Col>
        <Col md={2}>
          <Label check for="exampleCheck">
            Sort By Date
          </Label>
          <Input id="exampleCheck" name="check" type="checkbox" />
        </Col>
      </Row>
    </div>
}