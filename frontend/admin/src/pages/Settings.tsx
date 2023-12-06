// components
import Title from "components/atomic/atoms/Title";
import RemitList from "components/templetes/Remittance/RemitList";
// data
import { tableList } from "data/dummyData";

const Settings = () => {
  return (
    <div className="main-content contentWrap">
      <Title title={"부서 및 직원관리"} />
      <RemitList list={tableList} />
    </div>
  );
};

export default Settings;
