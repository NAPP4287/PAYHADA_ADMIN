// components
import ListFilter from "components/layout/ListFilter";
import ListTable from "components/layout/ListTable";
// data
import { TXN_TABLE_INFO } from "data/tableInfoData";

const RemitList = (props: any) => {
  const { list } = props;
  return (
    <>
      <ListFilter />
      <div className="marginTop">
        <ListTable info={TXN_TABLE_INFO} list={list} />
      </div>
    </>
  );
};

export default RemitList;
