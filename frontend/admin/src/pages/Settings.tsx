// components
import Title from "components/atomic/atoms/Title";
import ListTable from "components/layout/ListTable";
// data
import { tableList } from "data/dummyData";
import { TXN_TABLE_INFO } from "data/tableInfoData";

const Settings = () => {
  return (
    <div className="main-content contentWrap">
      <Title title={"부서 및 직원관리"} />
      <ListTable list={tableList} info={TXN_TABLE_INFO} />
    </div>
  );
};

export default Settings;
