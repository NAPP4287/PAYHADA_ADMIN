import { Pagination, PaginationItem, PaginationLink } from "reactstrap";
// data
import { paginationList } from "data/dummyData";

const PaginationBtn = () => {
  return (
    <div className="smarginTop">
      <nav aria-label="Page navigation example">
        <Pagination
          className="pagination justify-content-center pagination-sm"
          listClassName="justify-content-center pagination-sm">
          {paginationList.prev && (
            <PaginationItem className="disabled">
              <PaginationLink href="#pablo" onClick={(e) => e.preventDefault()}>
                <i className="ni ni-bold-left" />
              </PaginationLink>
            </PaginationItem>
          )}

          {paginationList.pageList.map((el, idx) => (
            <PaginationItem
              key={idx}
              className={
                Number(paginationList.page) === idx + 1 ? "active" : ""
              }>
              <PaginationLink href="#pablo" onClick={(e) => e.preventDefault()}>
                {el}
              </PaginationLink>
            </PaginationItem>
          ))}

          {paginationList.next && (
            <PaginationItem>
              <PaginationLink href="#pablo" onClick={(e) => e.preventDefault()}>
                <i className="ni ni-bold-right" />
              </PaginationLink>
            </PaginationItem>
          )}
        </Pagination>
      </nav>
    </div>
  );
};

export default PaginationBtn;
