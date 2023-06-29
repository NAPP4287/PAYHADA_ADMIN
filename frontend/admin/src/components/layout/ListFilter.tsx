import { Card, CardBody } from "reactstrap";
// components
import FilterRow from "components/atomic/atoms/FilterRow";
import AtomButton from "components/atomic/atoms/AtomButton";
// interface
import { ListFilterProps } from "interface/InterfaceProps";

const ListFilter = (props: ListFilterProps) => {
  const { filterTableList, action, tab, flexRate } = props;

  return (
    <Card className={`cardWrap ${tab && "tabCardStyle"}`}>
      <CardBody>
        <div
          className="marginBottom"
          style={{ display: "flex", justifyContent: "flex-end" }}>
          <AtomButton
            title={"조회"}
            type="primary"
            size="md"
            action={() => action}
          />
        </div>
        <div
          style={{
            borderTop: "1px solid #c9c9c9",
            flex: flexRate,
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
