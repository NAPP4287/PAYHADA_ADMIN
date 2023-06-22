import { useState } from "react";
import { Card, CardBody } from "reactstrap";
// components
import LabelSelect from "components/atomic/atoms/LabelSelect";
import DatePickers from "components/atomic/atoms/DatePickers";
// data
import { CALENDAR_DATE_LIST } from "data/selectData";

const ListFilter = () => {
  const [changeDate, setChangeDate] = useState("");

  return (
    <Card>
      <CardBody
        className="alignCenter"
        style={{
          justifyContent: "flex-start",
        }}>
        <LabelSelect
          type={"select"}
          setChangeData={setChangeDate}
          value={changeDate}
          dataArray={CALENDAR_DATE_LIST}
        />
        <div className="marginLeft">
          <DatePickers />
        </div>
      </CardBody>
    </Card>
  );
};

export default ListFilter;
