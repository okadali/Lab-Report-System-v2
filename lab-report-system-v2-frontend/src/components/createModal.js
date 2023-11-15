import { Modal } from "reactstrap";

const CreateModal = ({ isOpen, toggle }) => {
  return (
    <Modal isOpen={isOpen} toggle={toggle} >
      <h1>haha</h1>
    </Modal>
  )
}

export default CreateModal;