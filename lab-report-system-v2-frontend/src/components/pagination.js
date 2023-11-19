import { useEffect, useState } from "react";
import { useGlobalContext } from "../context/context";
import { Pagination, PaginationItem, PaginationLink } from "reactstrap";

const PaginationHolder = () => {
  const { pagination, setPagination,setRefresh } = useGlobalContext();
  const [paginationState, setPaginationState] = useState([]);

  const { page, totalPage } = pagination;

  useEffect(() => {
    const paginationArray = [];
    for (let i = 0; i <= totalPage; i++) {
      paginationArray.push(
        <PaginationItem
          active={page == i}
          onClick={() => {
            setPagination({ ...pagination, page: i });
            setRefresh(true)
          }}
        >
          <PaginationLink>{i + 1}</PaginationLink>
        </PaginationItem>
      );
    }
    setPaginationState(paginationArray);
  }, [pagination]);

  return (
    <div className="flex justify-center">
      <Pagination>{paginationState}</Pagination>
    </div>
  );
};

export default PaginationHolder;
