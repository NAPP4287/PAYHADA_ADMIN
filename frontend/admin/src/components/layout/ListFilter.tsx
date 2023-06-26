import { Card, CardBody } from "reactstrap";
// components
import FilterRow from "components/atomic/atoms/FilterRow";
import AtomButton from "components/atomic/atoms/AtomButton";

const ListFilter = (props: any) => {
  const { filterTableList } = props;

  return (
    <Card className="filterWrap">
      <CardBody>
        <div
          className="marginBottom"
          style={{ display: "flex", justifyContent: "flex-end" }}>
          <AtomButton type="primary" size="md" title={"조회"} />
        </div>
        <div
          style={{
            borderTop: "1px solid #c9c9c9",
          }}>
          {filterTableList.map((el: any, idx: number) => (
            <FilterRow rowList={el} key={idx} />
          ))}
        </div>
      </CardBody>
    </Card>
  );
};

export default ListFilter;
